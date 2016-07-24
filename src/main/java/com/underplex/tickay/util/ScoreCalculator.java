package com.underplex.tickay.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.underplex.tickay.game.Game;
import com.underplex.tickay.game.Path;
import com.underplex.tickay.jaxb.FinalScoringEntry;
import com.underplex.tickay.jaxb.PathEntry;
import com.underplex.tickay.jaxb.TicketPointsEntry;
import com.underplex.tickay.player.Player;

/**
 * Helping class for calculating scores.
 * <p>
 * Many of the abstract methods are meant to be implemented to return subclasses of <code>FinalScoringEntry</code>.
 * <p>
 * Extending this class will probably involve implementing <code>makeExpansionEntry</code> so it returns an expansion-specific subclass of
 * <code>FinalScoringEntry</code>. Then, when something specific to the expansion needs to be considered, the entry is passed to it with the idea
 * that it will be cast to the subclass with references to methods/fields that the base class doesn't have.
 * @author Brandon Irvine
 */
public abstract class ScoreCalculator implements Comparator<FinalScoringEntry>{

	protected Game game;
	
	public ScoreCalculator( Game game ){
		this.game = game;
	}
	
	/**
	 * Returns List of FinalScoringEntry elements for respective player.
	 */
	public abstract List<?> finalScoring();
	
	public Map<Player, FinalScoringEntry> makeEntries(){

		Map<Player, FinalScoringEntry> entries = new HashMap<>();
		Map<Player, Set<Path>> longestPaths = game.longestPaths();  // find all the longest paths by player (some may have tied)
		
		// for each player, make a final scoring entry that is complete/correct except for winner field
		for ( Player p: game.getPlayers().turnOrder() ){
			FinalScoringEntry entry = this.makeExpansionEntry();
			this.completeBaseEntry( entry, p, longestPaths ); // side-effects complete most points
			this.completeExpansionEntries( entry, p ); // add anything appropriate to it
			entries.put( p, entry );
		}
		
		return entries;
	}
	
	/**
	 * Side-effect of marking every winning player's entry as winner given <code>List</code> already ordered by score.
	 */
	public void winners( List<? extends FinalScoringEntry> scores ){	
		// now entries is Map with all player as keys and FinalScoreEntry for each that is complete except for having winner field

		Set<FinalScoringEntry> winners = new HashSet<>();

		FinalScoringEntry best = scores.get(0);

		winners.add( best );
		
		for ( int i = 1; i < scores.size(); i++ )
			if ( this.compare( best, scores.get(i) ) == 0 ) 
				winners.add( scores.get(i) );	

		for ( FinalScoringEntry scoring : scores ) {
			if ( winners.contains( scoring ) ){
				scoring.setWinner( true );
			} else {
				scoring.setWinner( false );
			}
		}		
	}

	/**
	 * Returns negative integer if entry1 should lose to entry2, 0 if they are completely tied, and positive integer if entry1 should win.
	 * <p>
	 * Parameters need to have true and accurate information about their respective player, except for reporting on whether they are a winner.
	 */
	public int compare( FinalScoringEntry entry1, FinalScoringEntry entry2){
		return 0;
	}

	/**
	 * Given a new entry, has side effect of adding all the base-game elements of a final score to it.
	 * <p>
	 * Depending on the game or version of this game, this may not make (and probably doesn't) the <code>entry</code> complete.
	 */
	protected void completeBaseEntry( FinalScoringEntry entry, Player p, Map<Player, Set<Path>> longestPaths ){
		
		entry.setPlayer( p.getPlayerType() );
		entry.setStrategyClass( p.getStrategy().getClass().getSimpleName() );
		entry.setRegularPoints( p.getPoints().count() );

		List<TicketPointsEntry> ticketPoints = entry.getTicketPoints(); 
		ticketPoints.addAll( p.getTickets().makeEntries() ); // add all entries giving ticket points

		// now deal with longest track issues
		List<PathEntry> pathEntries = new ArrayList<>();
		PathEntry pathEntry;
		
		// now we check if any of the longest paths belong to the player being iterated through
		for ( Path path : longestPaths.get( p ) ){
			pathEntry = new PathEntry();
			pathEntry.getRoute().addAll( path.getRouteStrings() );
			// no station coverages can count here, so no routes through station coverage should be assigned
			pathEntries.add( pathEntry );
		}	
		entry.getLongestTrack().addAll( pathEntries ); // set the longest track list to be the list of path entries we created

		entry.setFinalScore( calcBaseScore( entry ) + this.calcExpansionScore( entry ));

	}
	
	/**
	 * Returns score from basic game information given <code>FinalScoringEntry</code> that is complete except for winner field.
	 */
	protected int calcBaseScore( FinalScoringEntry entry ){
		int rInt = 0;
		rInt += entry.getRegularPoints();

		if ( !entry.getLongestTrack().isEmpty() )
			rInt += 10; // add 10 points if this player is among those with longest track
		for ( TicketPointsEntry tpe : entry.getTicketPoints() ){
			if ( tpe.isCompleted() ){
				rInt += tpe.getPoints();
			} else {
				rInt -= tpe.getPoints(); // per rules, lose points based on value of uncomplete ticket
			}
		}
		return rInt;
	}

	/**
	 * Returns number of completed tickets given <code>FinalScoringEntry</code> that is complete.
	 */
	protected int countTickets( FinalScoringEntry entry ){
		int rInt = 0;
		for ( TicketPointsEntry tpe : entry.getTicketPoints() )
			if ( tpe.isCompleted() )
				rInt++;
		return rInt;
	}
	
	/**
	 * Returns new <code>FinalScoringEntry</code> appropriate for the expansion being used.
	 */
	protected abstract FinalScoringEntry makeExpansionEntry();
	
	/**
	 * As side-effects, add expansion-specific entries to <code>entry</code>.
	 */
	protected abstract void completeExpansionEntries( FinalScoringEntry entry, Player player );
	
	/**
	 * Returns any expansion-specific scoring.
	 */
	protected abstract int calcExpansionScore( FinalScoringEntry entry );
	
	/**
	 * Returns <code>FinalScoringEntry</code> that belongs to the player or <code>null</code> if not found.
	 * <p>
	 * If more than two belong to the player, any might be returned.
	 */
	public static FinalScoringEntry findByPlayer( Collection<? extends FinalScoringEntry> entries, Player player ){
		FinalScoringEntry rEntry = null;
		for ( FinalScoringEntry entry : entries )
			if ( entry.getPlayer() == player.getPlayerType() ){ 
				rEntry = entry;
			}
		return rEntry;
	}
}
