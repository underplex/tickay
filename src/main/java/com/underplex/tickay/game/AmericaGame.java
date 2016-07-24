package com.underplex.tickay.game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.infowrap.InfoSource;
import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.info.AmericaGameInfo;
import com.underplex.tickay.info.PlayInfo;
import com.underplex.tickay.jaxb.AmericaGameEntry;
import com.underplex.tickay.jaxb.SetupPlayerEntry;
import com.underplex.tickay.jaxb.StrategyErrorEntry;
import com.underplex.tickay.jaxb.StrategyErrorType;
import com.underplex.tickay.jaxb.TurnEntry;
import com.underplex.tickay.play.Play;
import com.underplex.tickay.player.AmericaPlayer;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.AmericaStrategy;
import com.underplex.tickay.util.AmericaScoreCalculator;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Picker;

/**
 * Represents game of Ticket to Ride (base set, America map).
 * @author Brandon Irvine
 *
 */
public class AmericaGame extends Game implements InfoSource<AmericaGameInfo>{

	private AmericaGameInfo info;
	private AmericaGameEntry gameEntry;
	
	/**
	 * Default constructor.
	 * @param amerStrats	strategies ordered by seating position, 0 in first seat, etc.
	 */
	public AmericaGame( List<AmericaStrategy> amerStrats, String expansions ){

		super(expansions);
		
		AmericaPlayer player;
		// seat strategies
		for (int i = 0; i < amerStrats.size(); i++ ){
			player = new AmericaPlayer( amerStrats.get(i), i + 1, this );
			players.assignSeat( player, i + 1 );
		}
		
		this.gameEntry = new AmericaGameEntry();
		this.info = new AmericaGameInfo( this );
		
	} // end constructor

	public AmericaGameInfo info(){
		return this.info;
	}

	public AmericaGameEntry getGameEntry(){
		return this.gameEntry;
	}
	
	/**
	 * Run the game and return <code>AmericaGameEntry</code> representing complete record of the game.
	 */
	public AmericaGameEntry run(){

		// use the inherited Game setup
		gameEntry.setSetup( this.setup() );

		// do first turn, add appropriate entries to GameEntry
		this.firstTurn( gameEntry );
				
		phases.setPhase( PhaseType.REGULAR_TURNS ); // begin regular turns

		AmericaPlayer first = (AmericaPlayer)players.turnOrder().get(0);
		AmericaPlayer current = first;
		AmericaPlayer endPlayer = null;

		List<TurnEntry> turns = gameEntry.getTurn();

		// play regular turns
		while ( endPlayer == null ){
			
			if ( current == first )
				phases.advanceTurn(); // turn number advances
			
			turns.add( runTurn( current ) ); // current player takes a turn
			
			// when endPlayer is set to reference other than null, then the game is on its last turn
			if ( current.getPieces().endsGame() )
				endPlayer = current;
			
			current = (AmericaPlayer)players.next( current ); // returns next player in sequence
		} // end while

		// do last turns
		phases.setPhase( PhaseType.LAST_TURNS ); // begin last turns

		// play last turns
		do {
			if (current == first)
				phases.advanceTurn();
			
			turns.add( runTurn(current) );
			current = (AmericaPlayer)this.players.next( current ); // returns next player in sequence
			
		} while (current != players.next( endPlayer ) ); // check we're not giving anybody a second last turn

		phases.setTurn(0); // turns doesn't have significance anymore

		AmericaScoreCalculator calc = new AmericaScoreCalculator( this ); // instantiate helper class
		// use helper class to generate FinalScoringEntry elements
		gameEntry.getFinalScoring().addAll( calc.finalScoring() ); 

		return gameEntry;
	}

	public TurnEntry runTurn( Player current ){

		AmericaPlayer player = (AmericaPlayer)current;

		TurnEntry entry = new TurnEntry();
		entry.setTurn( phases.getTurn() );
		entry.setPlayer( player.getPlayerType() );

		Set<Play> options = PlayFinder.findRegularPlays( this, player ); // all regular plays that are legal
		
		Set<PlayInfo> optionsInfo = InfoWrapper.wrapToSet( options ); // immutable versions of legal plays
		PlayInfo decisionInfo = player.getStrategy().chooseRegularPlay( this.info(), player.myInfo(), new HashSet<PlayInfo>( optionsInfo ) );

		// decision validation
		if ( !optionsInfo.contains( decisionInfo ) ){

			StrategyErrorEntry errorEntry = new StrategyErrorEntry();
			errorEntry.setStrategyClass( player.getStrategy().getClass().getSimpleName() );
			errorEntry.setStrategyError( StrategyErrorType.REGULAR_TURN_CHOICE );
			entry.setError( errorEntry );

			// randomly select among legal options
			decisionInfo = Picker.selectRandom( optionsInfo );
		}

		Play decision = InfoWrapper.unwrap( options, decisionInfo );
		entry.setPlayType( decision.getPlayType() );

		// resolve the play the player's chosen and record it to the TurnEntry log entry
		this.assignPlay( entry, decision.resolve( this, player ) );

		return entry;
	}

	/**
	 * Sets up tickets for player, has side effects of changing player and the setup entry.
	 */
	public void setupTickets( SetupPlayerEntry entry, Player player  ){
		
		for ( int i = 1; i <= 3; i++ ){
			entry.getTicket().add( this.tickets.getShorts().get(0).toString() );
			player.getTickets().addTicket( this.tickets.getShorts().get(0) );
			this.tickets.getShorts().remove(0);
		}
			
	}
	
	/**
	 * Returns deck of tickets (where first discards go in this version of game).
	 */
	public List<Ticket> getFirstDiscardTarget(){
		return this.tickets.getShorts();
	}
}
