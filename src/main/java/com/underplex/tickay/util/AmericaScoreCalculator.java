package com.underplex.tickay.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.underplex.tickay.game.AmericaGame;
import com.underplex.tickay.jaxb.FinalScoringEntry;
import com.underplex.tickay.player.Player;

/**
 * Helping class for calculating scores in America version of the game.
 * @author Brandon Irvine
 */
public class AmericaScoreCalculator extends ScoreCalculator {

	private AmericaGame americaGame;
	
	public AmericaScoreCalculator( AmericaGame americaGame ){
		super(americaGame);
		this.americaGame = americaGame;
	}
	
	/**
	 * Returns List in turn order of FinalScoringEntry elements for respective player.
	 */
	public List<FinalScoringEntry> finalScoring(){

		Map<Player, FinalScoringEntry> entries = this.makeEntries(); // this should produce map that still has AmericaFinalScoringEntry elements mapped to Players

		// now entries is Map with all player as keys and FinalScoreEntry for each that is complete except for having winner field
		
		// extract FinalScoringEntries and cast to America ones, do same for Players
		
		List<FinalScoringEntry> scores = new ArrayList<FinalScoringEntry>();
		
		for ( FinalScoringEntry baseEntry : entries.values() )
			scores.add( baseEntry );
		
		Collections.sort( scores, this );
		Collections.reverse( scores );		
		
		winners( scores );
		
		return scores;
	}

	/**
	 * Returns negative integer if entry1 should lose to entry2, 0 if they are completely tied, and positive integer if entry1 should win.
	 * <p>
	 * Parameters need to have true and accurate information about their respective player, except for reporting on whether they are a winner.
	 */
	public int compare( FinalScoringEntry entry1, FinalScoringEntry entry2 ){

		/**From the Europe rules: "The player with the most points wins the americaGame.
		 * If two or more player are tied with the most points, the player who has completed the most Destination	Tickets is the winner.
		 * If still tied, the player who used the least number of Stations is declared the winner.
		 * In the unlikely event player are still tied, the player with the Americapean Express bonus card wins."
		 */
		int rValue = 99; // entry1 is better than entry2 as default

		if ( entry2.getFinalScore() > entry1.getFinalScore() ){
			rValue = -99;
		} else if ( entry2.getFinalScore() == entry1.getFinalScore() ) {
			if ( countTickets( entry2 ) > countTickets( entry1 ) ) {
				rValue = -99;
			} else if ( countTickets( entry2 ) == countTickets( entry1 ) ) {
				// this is a quick test for whether they have a longest track or not
				if ( !entry2.getLongestTrack().isEmpty() && entry1.getLongestTrack().isEmpty() ) { 
					rValue = -99;
				} else if ( !entry2.getLongestTrack().isEmpty() && !entry1.getLongestTrack().isEmpty() ) {
					rValue = 0;
				}
			}  
		}
		return rValue;    	
	}

	protected FinalScoringEntry makeExpansionEntry(){
		return new FinalScoringEntry();
	}

	/**
	 * Stub method that does nothing, because in the America version of this game nothing is added to final scoring.
	 */
	protected void completeExpansionEntries( FinalScoringEntry entry, Player player ){

	}
	
	protected int calcExpansionScore( FinalScoringEntry entry ){
		return 0;
	}
	
}
