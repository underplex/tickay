package com.underplex.tickay.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.infowrap.InfoSource;
import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.info.EuroGameInfo;
import com.underplex.tickay.info.PlayInfo;
import com.underplex.tickay.info.RouteInfo;
import com.underplex.tickay.jaxb.EuroGameEntry;
import com.underplex.tickay.jaxb.SetupPlayerEntry;
import com.underplex.tickay.jaxb.StationChoiceEntry;
import com.underplex.tickay.jaxb.StrategyErrorEntry;
import com.underplex.tickay.jaxb.StrategyErrorType;
import com.underplex.tickay.jaxb.TurnEntry;
import com.underplex.tickay.jaxbinfo.EuroGameEntryInfo;
import com.underplex.tickay.play.Play;
import com.underplex.tickay.player.EuroPlayer;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.util.EuroScoreCalculator;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Picker;

/**
 * Represents game of Ticket to Ride Europe.
 * @author Brandon Irvine
 *
 */
public class EuroGame extends Game implements InfoSource<EuroGameInfo>{

	private EuroGameInfo info;
	private EuroGameEntry gameEntry;
	private EuroGameEntryInfo gameEntryInfo;
	
	/**
	 * Default constructor.
	 * @param euroStrategies	strategies ordered by seating position, 0 in first seat, etc.
	 */
	public EuroGame( List<? extends EuroStrategy> euroStrategies, String expansions ){

		super(expansions);
		
		EuroPlayer player;
		// seat strategies
		for (int i = 0; i < euroStrategies.size(); i++ ){
			player = new EuroPlayer( euroStrategies.get(i), i + 1, this );
			players.assignSeat( player, i + 1 );
		}
		
		this.gameEntry = new EuroGameEntry();
		this.gameEntryInfo = new EuroGameEntryInfo(this.gameEntry);
		this.info = new EuroGameInfo( this );
		
	} // end constructor

	public EuroGameInfo info(){
		return this.info;
	}

	public EuroGameEntry getGameEntry(){
		return this.gameEntry;
	}
	
	/**
	 * Runs complete game and returns <code>EuroGameEntry</code> record of entire game.
	 */
	public EuroGameEntry run(){

		// use the inherited Game setup
		gameEntry.setSetup( this.setup() );

		// do first turn, add appropriate entries to GameEntry
		this.firstTurn( gameEntry );
				
		phases.setPhase( PhaseType.REGULAR_TURNS ); // begin regular turns

		EuroPlayer first = (EuroPlayer)players.turnOrder().get(0);
		EuroPlayer current = first;
		EuroPlayer endPlayer = null;

		List<TurnEntry> turns = gameEntry.getTurn();

		// play regular turns
		while ( endPlayer == null ){
			
			if ( current == first )
				phases.advanceTurn(); // turn number advances
			
			turns.add( runTurn( current ) ); // current player takes a turn
			
			// when endPlayer is set to reference other than null, then the game is on its last turn
			if ( current.getPieces().endsGame() )
				endPlayer = current;
			
			current = (EuroPlayer)players.next( current ); // returns next player in sequence
		} // end while

		// do last turns
		phases.setPhase( PhaseType.LAST_TURNS ); // begin last turns

		// play last turns
		do {
			if (current == first)
				phases.advanceTurn();
			
			turns.add( runTurn(current) );
			current = (EuroPlayer)this.players.next( current ); // returns next player in sequence
			
		} while (current != players.next( endPlayer ) ); // check we're not giving anybody a second last turn

		phases.setTurn(0); // turns doesn't have significance anymore
		phases.setPhase( PhaseType.STATION_COVERAGE_CHOICES);

		List<StationChoiceEntry> stationChoices = gameEntry.getStationChoice();

		// continue by doing station choices with the player next to the endPlayer
		do {
			stationChoices.addAll( chooseStationRoutes( current ) );
			current = (EuroPlayer)players.next(current); // returns next player in sequence
		} while (current != players.next(endPlayer) ); // check we're not giving anybody a second station choice turn

		EuroScoreCalculator calc = new EuroScoreCalculator( this ); // instantiate helper class
		// use helper class to generate FinalScoringEntry elements
		gameEntry.getFinalScoring().addAll( calc.finalScoring() ); 

		return gameEntry;
	}

	/**
	 * Returns list of entries representing station choices for player.
	 */
	public List<StationChoiceEntry> chooseStationRoutes( EuroPlayer current ){
		List<StationChoiceEntry> entries = new ArrayList<>();

		StrategyErrorEntry errorEntry = null;

		StationChoiceEntry entry = new StationChoiceEntry();

		Set<Route> choices;
		Set<RouteInfo> choicesInfo;
		Route decision;
		RouteInfo decisionInfo;

		for ( City city: current.getStations().built() ) {  // for each station built by this player

			entry = new StationChoiceEntry();

			choices = current.getStations().coverageFrom( city ); // at worst an empty set per definition of method

			if ( !choices.isEmpty() ){
				choicesInfo = InfoWrapper.wrapToSet( choices ); // create wrapped set
				decisionInfo = current.getStrategy().chooseStationExtension( this.info(), current.myInfo(), city.info(), new HashSet<RouteInfo>( choicesInfo ) );

				if ( !choicesInfo.contains( decisionInfo )) { // validation/error generation

					// record error as entry
					errorEntry = new StrategyErrorEntry();
					errorEntry.setStrategyError( StrategyErrorType.STATION_COVERAGE_CHOICE );
					entry.setError( errorEntry );
					
					// force the actual decision to be something legal
					List<RouteInfo> choiceList = new ArrayList<RouteInfo>(choicesInfo);
					decisionInfo = choiceList.get(0);
				} // end if !choicesInfo.contains(decisionInfo)

				decision = InfoWrapper.unwrap( choices, decisionInfo );
				current.getNetworks().addStationDecision( city, decision );	// add the route to the player's networks as a "station route"        

			} else { // if choices.size().isEmpty
				entry.setNoAvailableRoutes(true); 
			}

		} // end for city
		entries.add(entry);

		return entries;
	}

	public TurnEntry runTurn( Player current ){

		EuroPlayer player = (EuroPlayer)current;

		TurnEntry entry = new TurnEntry();
		entry.setTurn( phases.getTurn() );
		entry.setPlayer( player.getPlayerType() );

		Set<Play> options = PlayFinder.findRegularPlays( this, player ); // all regular plays that are legal
		options.addAll(PlayFinder.findStationPlays(this, player)); // add all the station claims possible
		
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
	 * Sets up tickets at beginning of game for player, has side effects of changing player and the setup entry.
	 */
	public void setupTickets( SetupPlayerEntry entry, Player player  ){
		entry.getTicket().add( this.tickets.getLongs().get(0).toString() );
		player.getTickets().addTicket( this.tickets.getLongs().get(0) );
		this.tickets.getLongs().remove(0);
		
		for ( int i = 1; i <= 3; i++ ){
			entry.getTicket().add( this.tickets.getShorts().get(0).toString() );
			player.getTickets().addTicket( this.tickets.getShorts().get(0) );
			this.tickets.getShorts().remove(0);
		}
	}

	/**
	 * Returns oblivion list (where first discards go).
	 */
	public List<Ticket> getFirstDiscardTarget() {
		return this.tickets.getOblivion();
	}
}
