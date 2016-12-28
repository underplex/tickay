package com.underplex.tickay.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.info.GameInfo;
import com.underplex.tickay.info.TicketInfo;
import com.underplex.tickay.jaxb.BuildStationEntry;
import com.underplex.tickay.jaxb.ClaimRouteEntry;
import com.underplex.tickay.jaxb.DeckDrawEntry;
import com.underplex.tickay.jaxb.DrawTicketsEntry;
import com.underplex.tickay.jaxb.FirstDiscardEntry;
import com.underplex.tickay.jaxb.GameEntry;
import com.underplex.tickay.jaxb.PlayerDefinitionEntry;
import com.underplex.tickay.jaxb.SetupEntry;
import com.underplex.tickay.jaxb.SetupPlayerEntry;
import com.underplex.tickay.jaxb.StrategyErrorEntry;
import com.underplex.tickay.jaxb.StrategyErrorType;
import com.underplex.tickay.jaxb.TakeTrainsEntry;
import com.underplex.tickay.jaxb.TurnEntry;
import com.underplex.tickay.player.Player;

public abstract class Game {

	protected final PhaseManager phases;
	protected final BoardManager board;
	protected final TicketManager tickets;
	protected final TrainManager trains;
	protected final PlayerManager players;

	/**
	 * Default constructor.
	 * @param expansions	<code>String</code> representing version and all expansions used
	 */
	protected Game( String expansions ){

		this.players = new PlayerManager( expansions, this );
		this.phases = new PhaseManager( expansions, this );
		this.board = new BoardManager( expansions, this );
		this.trains = new TrainManager( expansions, this );
		this.tickets = new TicketManager( expansions, this, this.board.getCities() );

	} // end constructor
	
	public abstract GameInfo info();
	
	public PlayerManager getPlayers() {
		return this.players;
	}
	
	public PhaseManager getPhases() {
		return phases;
	}

	public BoardManager getBoard() {
		return board;
	}

	public TicketManager getTickets() {
		return tickets;
	}

	public TrainManager getTrains() {
		return trains;
	}
	
	/**
	 * Sets up game and returns SetupEntry representing the setup before any play are made.
	 * <p>
	 * At this point, the game exists but the elements have not been "laid on the table" or given to player to play.
	 */
	public SetupEntry setup(){
		
		SetupEntry entry = new SetupEntry();
		entry.setNumberOfPlayers( players.size() );
		List<PlayerDefinitionEntry> definitions = entry.getPlayerDefinition();
		List<SetupPlayerEntry> setupPlayers = entry.getSetupPlayer();
		PlayerDefinitionEntry definitionEntry;
		SetupPlayerEntry setupPlayerEntry;  
		DeckDrawEntry trainDraw;
		
		for (Player player: players.turnOrder() ){
			definitionEntry = new PlayerDefinitionEntry();
			definitionEntry.setPlayerType( player.getPlayerType() );
			definitionEntry.setStrategyIdentifier( player.getStrategy().getIdentifier() );
			definitionEntry.setStrategyToString( player.getStrategy().toString() );
			definitions.add( definitionEntry );
			
			setupPlayerEntry = new SetupPlayerEntry();
			for ( int i = 1; i <= 4; i++ ){
				trainDraw = this.trains.draw();
				setupPlayerEntry.getTrain().add( trainDraw );
				player.getTrains().add( trainDraw.getTrainType() );
			}
			
			setupPlayerEntry.setPlayer( player.getPlayerType() );
			
			setupTickets( setupPlayerEntry, player );			
			setupPlayers.add( setupPlayerEntry );
			
		}
		
		// add any wipes that are caused by the inital setup
		entry.getWipe().addAll( trains.setUpFaceUps() );

		// add info about the initial trains
		entry.getInitialFaceUp().addAll( this.trains.getFaceUps() );
		
		return entry;
	}

	/**
	 * Sets up tickets for player, has side effects of changing player and the setup entry.
	 */
	public void setupTickets( SetupPlayerEntry entry, Player player ){
		// write method with side-effect of updating things
		
		for ( int i = 1; i <= 3; i++ ){
			entry.getTicket().add( this.tickets.getShorts().get(0).toString() );
			player.getTickets().addTicket( this.tickets.getShorts().get(0) );
			this.tickets.getShorts().remove(0);
		}
	}
		
	/**
	 * Asks player to choose their initial discards of tickets.	 *  
	 */
	protected FirstDiscardEntry firstDiscard( Player player ){

		FirstDiscardEntry entry = new FirstDiscardEntry();
		entry.setPlayer( player.getPlayerType() );
		
		Set<TicketInfo> decisions = player.getStrategy().discardFirstTickets( this.info(), player.myInfo(), 
				InfoWrapper.wrapToSet( player.getTickets().getTickets() ) );

		// check for valid decision and set to a valid decision if it wasn't
		if ( !ChoiceValidator.firstDiscard( player.getTickets().getTickets(), decisions ) ){
			// create and add an entry representing a failure by strategy class to return valid decision
			StrategyErrorEntry errorEntry = new StrategyErrorEntry();
			errorEntry.setStrategyClass( player.getStrategy().getClass().getSimpleName() );
			errorEntry.setStrategyError(StrategyErrorType.FIRST_DISCARD_CHOICE); 
			entry.setFirstDiscardChoiceError( errorEntry );
			
			// just create empty set representing no discards so the set is valid for rules of game
			decisions = new HashSet<TicketInfo>(); 
		}

		Set<Ticket> unwrapped = InfoWrapper.unwrapToSet( player.getTickets().getTickets(), decisions );
		for ( Ticket choice : new HashSet<>( player.getTickets().getTickets()) ){ // go through every legal choice -- i.e. every card in the hand
			if ( unwrapped.contains( choice ) ){ // if this is to be discarded
				player.getTickets().lose( choice );
				List<Ticket> targetList = this.getFirstDiscardTarget();
				targetList.add( choice ); // since this is among first discards, this card is permanently removed from game
				entry.getDiscard().add( choice.toString() );
			} else { // if this card is to be kept
				player.getTickets().addTicket( choice );
				entry.getKeep().add( choice.toString() );
			}
		}
		return entry;
		
	} // end method	
	
	/**
	 * Executes the first turn of the game, and adds entries as side-effect to <code>gameEntry</code>.
	 */
	protected void firstTurn(GameEntry gameEntry){

		// phase changes aren't recorded as entries
		phases.setPhase( PhaseType.FIRST_TURNS );
		phases.beginGame(); // sets turn counter to 1

		// do first discard and first turn (phases.getTurn() should == 1 right now)
		for ( Player player: players.turnOrder() ){
			gameEntry.getFirstDiscard().add( firstDiscard( player ) ); // add entry for discard of first turn
			gameEntry.getTurn().add( runTurn( player ) );	 // sets in entry the "ordinary" events of first turn        
		}
	}
	
	/**
	 * Runs a turn for the player and returns <code>TurnEntry</code> representing that turn.
	 */
	public abstract TurnEntry runTurn( Player player );
	
	/**
	 * Returns the Map that gives Set of Paths that are among longest in the game for each player.
	 * <p>
	 * The returned map will have any empty Set for any player that doesn't have a longest path.
	 */
	public Map<Player, Set<Path>> longestPaths(){
		Map<Player, Set<Path>> rMap = new HashMap<>();

		for ( Player p : players.turnOrder() )
			rMap.put(p, new HashSet<Path>() );  
	
		List<Path> allPaths = new ArrayList<>();
	
		for ( Player p: players.turnOrder() )
			allPaths.addAll( p.getNetworks().paths() );
	
		Collections.sort( allPaths );
		Collections.reverse( allPaths );
	
		int longest;
		int i;
		Player euroPlayer;
	
		if ( !allPaths.isEmpty() ){
			longest = allPaths.get(0).length(); // the first item will necessarily be the longest
			euroPlayer = allPaths.get(0).getPlayer(); // find its player
			rMap.get( euroPlayer ).add( allPaths.get(0) ); // add the path to that player's set of longest paths
			
			// now iterate through the other paths, knowing that they're in order from longest to shortest...
			i = 1;			
			while ( i < allPaths.size() && allPaths.get(i).length() == longest ){ // check that the current path is as long as longest
				euroPlayer = allPaths.get(i).getPlayer(); // find player for the current path
				rMap.get( euroPlayer ).add( allPaths.get(i) ); // add the path to that player's set of longest paths
				i++;
			} // end while             
		} // end if 

	return rMap;
	} // end method

	/**
	 * Updates entry with play, which must be one of a few types of Entry.
	 * <p>
	 * This method is used to cast the current play -- whatever it is -- to its appropriate type to be attached to the JAXB TurnEntry instance.
	 */
	protected void assignPlay( TurnEntry entry, Object play ){
		
		if ( play instanceof BuildStationEntry){
			entry.setBuildStation( (BuildStationEntry)play );
		} else if ( play instanceof ClaimRouteEntry ){
			entry.setClaimRoute( (ClaimRouteEntry)play );
		} else if ( play instanceof TakeTrainsEntry ){
			entry.setTakeTrains( ( TakeTrainsEntry)play );
		} else if ( play instanceof DrawTicketsEntry){
			entry.setDrawTickets( (DrawTicketsEntry)play );
		}		
	}

	/**
	 * Returns the list where tickets go when discarded for the first discard.
	 */
	public abstract List<Ticket> getFirstDiscardTarget();
		
	







}
