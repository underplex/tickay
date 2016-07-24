package com.underplex.tickay.play;

import java.util.HashSet;
import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.game.Payment;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.info.ClaimPlayInfo;
import com.underplex.tickay.info.PaymentInfo;
import com.underplex.tickay.jaxb.ClaimRouteEntry;
import com.underplex.tickay.jaxb.DeckDrawEntry;
import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.jaxb.StrategyErrorEntry;
import com.underplex.tickay.jaxb.StrategyErrorType;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.jaxb.TunnelingEntry;
import com.underplex.tickay.player.EuroPlayer;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.util.PaymentCalculator;

public class ClaimPlay implements Play {

	private ClaimPlayInfo info;
	private Route route;
	private Payment payment;

	public ClaimPlay( Route route, Payment payment ) {
		this.route = route;
		this.payment = payment;
		this.info = new ClaimPlayInfo(this);
	}
	
	public ClaimPlayInfo info() {
		return info;
	}

	public String toString(){
		return "PLAY: claim route " + route.toShortLabelString() + " paid by " + payment.toString();
	}

	public boolean equals(Object obj){
		boolean rBool = true;
		if ( !(obj instanceof ClaimPlay) ){
			rBool = false;
		} else {
			ClaimPlay other = (ClaimPlay)obj;
			if ( !other.getPayment().equals(this.payment))	rBool = false;
			if ( !other.getRoute().equals(this.route))	rBool = false;
		}
		
		return rBool;
	}
	
	public int hashCode(){
		return this.route.hashCode() * this.payment.hashCode();
	}
	
	public Route getRoute() {
		return route;
	}

	public Payment getPayment() {
		return payment;
	}

	public PlayType getPlayType(){
		return PlayType.CLAIM_ROUTE;
	}
	
	public ClaimRouteEntry resolve( Game game, Player player ) { 
		
		ClaimRouteEntry entry = new ClaimRouteEntry();
		// alter entry
		entry.setPlayer( player.getPlayerType() );
		entry.getPayment().addAll( payment.list() );
		entry.setRoute( this.route.toString() );

		if ( !this.route.isTunnel() ) {

			entry.setTunneling( null ); // no tunneling

			// make game state changes
			player.getTrains().pays( payment ); // pay the original cost
			player.getRoutes().addRoute( route ); // give the route to the player
			this.route.setBuilder( player );
			player.getPoints().addPoints( route.getPoints(game, player));
			player.getPieces().place( this.route.getLength() );
			
		} else if ( player instanceof EuroPlayer && game instanceof EuroGame ){ // that is, if the route is a tunnel
			
			entry.setTunneling( resolveTunnel( (EuroGame)game, (EuroPlayer)player ) );
			
		} // end if-else != tunnel

		return entry;
	}

	/**
	 * Returns route claim for a tunnel route and change game state accordingly.
	 */
	private TunnelingEntry resolveTunnel( EuroGame game, EuroPlayer player ){

		Set<DeckDrawEntry> draws = doFlips(game, player);
		int additional = analyzeFlips(game,player,draws);
		
		TunnelingEntry entry = new TunnelingEntry();
		
		entry.setAdditionalRequired( additional );
		entry.getAdditionalTurnedUp().addAll( draws );
		
		if ( additional == 0 ){
			entry.setCanPay( true );
			// assume player does choose to pay if no more costs are asked of him
			entry.setDoesPay( true ); 
			player.getTrains().pays( this.payment ); // pay the original cost
			player.getRoutes().addRoute( route ); // give the route to the player
			this.route.setBuilder( player );
			player.getPoints().addPoints( route.getPoints(game, player));
			
		} else {
			Set<Payment> tunnelOptions = PaymentCalculator.findTunnelPayments(game, player, this.payment, additional);
			if ( tunnelOptions.size() == 0 ) { // no way to pay additional payment
				entry.setCanPay( false );
				entry.setDoesPay( false );
			} else {
				entry.setCanPay( true );
				PaymentInfo choiceInfo = this.chooseTunnelPayment( game, player, tunnelOptions );
				Payment choice = InfoWrapper.unwrap( tunnelOptions, choiceInfo );
				this.resolveTunnelChoice( game, player, entry, choice );
			}	
		}
			
		return entry;
	}
	
	public Set<DeckDrawEntry> doFlips( Game game, Player player ){
		Set<DeckDrawEntry> draws = new HashSet<>();
		DeckDrawEntry draw;
		
		for (int i = 1; i <= 3; i++) {
			draw = game.getTrains().draw();
			draws.add( draw );
			if ( draw.getTrainType() != null )
				game.getTrains().toDiscard( draw.getTrainType() );
		} // end for i
		return draws;
	}

	public int analyzeFlips(Game game, Player player, Set<DeckDrawEntry> draws){

		int rInt = 0;
		for ( DeckDrawEntry draw: draws)
			// if the draw deck is empty, the TrainType will be null and thus will never match, so that case is okay
			if ( draw.getTrainType() == payment.getTrainType() || draw.getTrainType() == TrainType.LOCO )
				rInt++;
		
		return rInt;
	}
	
	private PaymentInfo chooseTunnelPayment(EuroGame game, EuroPlayer player, Set<Payment> tunnelOptions){
		Set<PaymentInfo> tunnelOptionsInfo = InfoWrapper.wrapToSet( tunnelOptions );
		PaymentInfo choiceInfo = player.getStrategy().chooseAdditionalPayment( game.info(), player.myInfo(), route.info(), 
				new HashSet<PaymentInfo>( tunnelOptionsInfo ) );
		// choice validation
		
		if ( ( choiceInfo != null ) && ( !tunnelOptionsInfo.contains( choiceInfo) ) ){
			
			StrategyErrorEntry error = new StrategyErrorEntry();
			error.setStrategyClass( player.getStrategy().getClass().getSimpleName() );
			error.setStrategyError( StrategyErrorType.TUNNEL_CHOICE);
			
			choiceInfo = null; // set to null, i.e. don't pay extra cards
		}
		return choiceInfo;
	}
	
	/**
	 * Changes the game state, player's state, and the entry's state depending on the choice to resolve the payment and building of a tunnel.
	 * <p>
	 * Assumes that choice is legal and will sufficiently pay for the additional costs imposed by the flip for a route.
	 */
	public void resolveTunnelChoice( Game game, Player player, TunnelingEntry entry, Payment choice){
	
		// recording and executing choice
		if ( choice == null ){
			entry.setDoesPay( false ); // player doesn't want to pay, so payment doesn't happen
		} else {
			entry.setDoesPay( true );
			entry.getSpentForAdditional().addAll( choice.list() );
			
			player.getTrains().pays( this.payment ); // pay the original cost
			player.getTrains().pays( choice ); // pay the additional cost
			player.getRoutes().addRoute( route ); // give the route to the player
			this.route.setBuilder( player );
			player.getPoints().addPoints( route.getPoints(game, player));

		}
	}
}