package com.underplex.tickay.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.tickay.game.City;
import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.game.Payment;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.game.Ticket;
import com.underplex.tickay.info.SecondTakePlayInfo;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.play.ClaimPlay;
import com.underplex.tickay.play.Play;
import com.underplex.tickay.play.StationPlay;
import com.underplex.tickay.play.TakeTrainsPlay;
import com.underplex.tickay.play.TicketChoicePlay;
import com.underplex.tickay.play.TicketPlay;
import com.underplex.tickay.player.EuroPlayer;
import com.underplex.tickay.player.Player;

/**
 * Utility class with static methods for finding all available play.
 */
public class PlayFinder {

	private PlayFinder() {
		// don't instantiate
	}

	/**
	 * Returns Set representing all the legal plays for player in the base game.
	 * <p>
	 * Additional options that depend on the version being played are not included.
	 */
	public static Set<Play> findRegularPlays( Game game, Player player ) {
		Set<Play> rSet = new HashSet<>();

		rSet.addAll(findTakeTrainPlays( game, player ));
		rSet.addAll(findClaimPlays( game, player ));
		rSet.addAll(findTicketPlays( game, player ));
		
		return rSet;
	}

	public static Set<TicketPlay> findTicketPlays( Game game, Player player ) {
		Set<TicketPlay> rSet = new HashSet<>();
		if ( game.getTickets().getShorts().size() > 0 ) // if there are no tickets, this isn't a legal play to choose
			rSet.add( new TicketPlay(game, player) );
		return rSet;
	}
	
	/**
	 * Returns all legal play that build a station.
	 */
	public static Set<StationPlay> findStationPlays( EuroGame euroGame, EuroPlayer euroPlayer ) {
		Set<StationPlay> rSet = new HashSet<>();
		int builtSize = euroPlayer.getStations().built().size();
		if (builtSize < 3) {
			Set<Payment> payments = PaymentCalculator.findWildPayments( builtSize + 1, euroPlayer.getTrains());

			Set<City> open = euroGame.getBoard().openCities();
			for (Payment payment : payments)
				for (City city : open)
					rSet.add( new StationPlay( city, payment ) );

		} // end if
		return rSet;
	}

	public static Set<TakeTrainsPlay> findTakeTrainPlays( Game game, Player player ) {
		Set<TakeTrainsPlay> rSet = new HashSet<>();
		// while the faceups might have two copies of a single TrainType, the copy to a new set will eliminate doubles
		Set<TrainType> options = new HashSet<TrainType>( game.getTrains().getFaceUps() );
		
		for ( TrainType type : options )
			rSet.add( new TakeTrainsPlay( player, type ) );

		// add the option to take a random draw from deck
		if ( !game.getTrains().isEmpty() )
			rSet.add( new TakeTrainsPlay( player, null ) );

		return rSet;
	}

	/**
	 * Returns set of ClaimPlays representing all the legal route claims <code>player</code> could make in the context of the current <code>game</code>.
	 * <p>
	 * Since all are added to a single set, the ClaimPlays returned won't be equal, but keep in mind that if two gray routes are paired together as twins,
	 * they are not equal and so will appear somewhat redundantly when looking at all options. That is, two gray routes side-by-side are functionally
	 * identical in the game, but are really separate so will have separate ClaimPlays associated with each.
	 */
	public static Set<ClaimPlay> findClaimPlays( Game game, Player player ) {
		Set<ClaimPlay> rSet = new HashSet<>();
		Set<Payment> payments;
		for ( Route r : game.getBoard().buildableRoutes( player ) ) {
			payments = new HashSet<>();
			if ( r.getColor() == TrainType.GRAY) {
				payments.addAll( PaymentCalculator.findWildPaymentsWithLocos( r.getLength(), player.getTrains(), r.getLocos() ) );
			} else {
				payments.addAll( PaymentCalculator.findPayments( r.getLength(), r.getColor(), player.getTrains() ) );
			}
			for (Payment p : payments)
				rSet.add( new ClaimPlay(r, p) );
		}
		return rSet;
	}

	/**
	 * Returns all legal second-take plays.
	 * <p>
	 * This assumes that the player is able to take a second card.
	 * <p>
	 */
	public static Set<SecondTakePlayInfo> findSecondTakePlays( Game game, Player Player ) {
		Set<SecondTakePlayInfo> rSet = new HashSet<>();
		// check corner case where deck and discard are completely empty; if not, include "blind draw" from deck
		if ( !game.getTrains().isEmpty() )
			rSet.add( new SecondTakePlayInfo( null ) );
		
		Set<TrainType> tSet = new HashSet<>( game.getTrains().getFaceUps() ); // eliminates doubles
		tSet.remove(TrainType.LOCO); // can't take a loco on second take
		for ( TrainType type : tSet )
			rSet.add( new SecondTakePlayInfo( type ) );

		return rSet;

	} // end method
	
	/**
	 * Finds all the legal play for choosing tickets from <code>tickets</code> drawn.
	 * <p>
	 * Set of tickets passed must be between 1 and 3 long. Otherwise, this method has undefined behavior.
	 */
	public static Set<TicketChoicePlay> findTicketChoicePlays( Game game,
			Player player, Set<Ticket> tickets ) {
		// remember, must keep at least 1
		List<Ticket> list = new ArrayList<Ticket>( tickets );
		// System.out.println(" in findTicketChoicePlays, list of " + list.size() + " is " + tickets );
		Set<TicketChoicePlay> rSet = new HashSet<>();
		
		//for testing
		TicketChoicePlay newTic;
		
		// there are a limited number of plays when considering 8 tickets and 1-3 to keep.
		// ...so we've just hard-coded the possible combinations
		if ( list.size() == 3 ){
			// remember TicketChoicePlay constructor takes the list and two arguments for tickets to keep
			
			newTic = new TicketChoicePlay( tickets, list.get(0), list.get(1)); 
			rSet.add( newTic );
			
			newTic = new TicketChoicePlay( tickets, list.get(0), list.get(2)); 
			rSet.add( newTic );

			newTic = new TicketChoicePlay( tickets, list.get(1), list.get(2)); 
			rSet.add( newTic );

			newTic = new TicketChoicePlay( tickets, list.get(2), list.get(1)); 
			rSet.add( newTic );

			newTic = new TicketChoicePlay( tickets, list.get(0), null); 
			rSet.add( newTic );
			
			newTic = new TicketChoicePlay( tickets, list.get(1), null); 
			rSet.add( newTic );
			
			newTic = new TicketChoicePlay( tickets, list.get(2), null); 
			rSet.add( newTic );
			
			newTic = new TicketChoicePlay( tickets, null, list.get(1)); 
			rSet.add( newTic );

			newTic = new TicketChoicePlay( tickets, null, null); 
			rSet.add( newTic );

			
		} else if ( list.size() == 2 ){
			rSet.add(new TicketChoicePlay(tickets, list.get(0), null));
			rSet.add(new TicketChoicePlay(tickets, list.get(1), null));
			rSet.add(new TicketChoicePlay(tickets, null, null));
		} else if ( list.size() == 1 ){ //only legal choice is to keep the single card drawn
			rSet.add(new TicketChoicePlay(tickets, null, null));
		}
		return rSet;

	}
	
}
