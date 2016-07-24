package com.underplex.tickay.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.jaxb.EuroFinalScoringEntry;
import com.underplex.tickay.jaxb.FinalScoringEntry;
import com.underplex.tickay.player.EuroPlayer;
import com.underplex.tickay.player.Player;

/**
 * Helping class for calculating scores.
 * <p>
 * Primary function is to calculate scores for a Ticket to Ride Europe game.
 * @author Brandon Irvine
 */
public class EuroScoreCalculator extends ScoreCalculator {

	private EuroGame euroGame;
	
	public EuroScoreCalculator( EuroGame euroGame ){
		super(euroGame);
		this.euroGame = euroGame;
	}
	
	/**
	 * Returns <code>List</code> of FinalScoringEntry elements for respective player.
	 */
	public List<EuroFinalScoringEntry> finalScoring(){

		Map<Player, FinalScoringEntry> entries = this.makeEntries(); // this should produce map that still has EuroFinalScoringEntry elements mapped to Players

		// now entries is Map with all player as keys and FinalScoreEntry for each that is complete except for having winner field
		
		// extract FinalScoringEntries and cast to Euro ones, do same for Players
		
		List<EuroFinalScoringEntry> scores = new ArrayList<EuroFinalScoringEntry>();
		
		for ( FinalScoringEntry baseEntry : entries.values() )
			if ( baseEntry instanceof EuroFinalScoringEntry )
				scores.add( (EuroFinalScoringEntry)baseEntry ); // cast the scoring entry to Euro version
		
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
	public int compare( FinalScoringEntry fEntry1, FinalScoringEntry fEntry2 ){

		int rInt = 0;
		EuroFinalScoringEntry entry1 = null;
		EuroFinalScoringEntry entry2 = null;
		if ( fEntry1 instanceof EuroFinalScoringEntry ){
			entry1 = (EuroFinalScoringEntry)fEntry1;
		}
		if ( fEntry2 instanceof EuroFinalScoringEntry ){
			entry2 = (EuroFinalScoringEntry)fEntry2;
		}
		
		if ( entry1 != null && entry2 != null ){
			rInt = euroCompare(entry1, entry2);
		} else {
			rInt = super.compare(fEntry1, fEntry2);
		}
		return rInt;
	}

	private int euroCompare ( EuroFinalScoringEntry entry1, EuroFinalScoringEntry entry2 ){

		/**From the Europe rules: "The player with the most points wins the euroGame.
		 * If two or more player are tied with the most points, the player who has completed the most Destination	Tickets is the winner.
		 * If still tied, the player who used the least number of Stations is declared the winner.
		 * In the unlikely event player are still tied, the player with the European Express bonus card wins."
		 */
		int rValue = 99; // entry1 is better than entry2 as default

		if ( entry2.getFinalScore() > entry1.getFinalScore() ){
			rValue = -99;
		} else if ( entry2.getFinalScore() == entry1.getFinalScore() ) {
			if ( countTickets( entry2 ) > countTickets( entry1 ) ) {
				rValue = -99;
			} else if ( countTickets( entry2 ) == countTickets( entry1 ) ) {
				if ( entry2.getStationPoints() > entry1.getStationPoints() ){
					rValue = -99;
				} else if ( entry2.getStationPoints() == entry1.getStationPoints() ) {
					// this is a quick test for whether they have a longest track or not
					if ( !entry2.getLongestTrack().isEmpty() && entry1.getLongestTrack().isEmpty() ) { 
						rValue = -99;
					} else if ( !entry2.getLongestTrack().isEmpty() && !entry1.getLongestTrack().isEmpty() ) {
						rValue = 0;
					}
				}
			}  
		}
		return rValue;    	
	}

	protected EuroFinalScoringEntry makeExpansionEntry(){
		return new EuroFinalScoringEntry();
	}
	
	protected void completeExpansionEntries( FinalScoringEntry entry, Player player ){
		
		if ( player instanceof EuroPlayer ){
			EuroPlayer p = (EuroPlayer)player;
			if ( entry instanceof EuroFinalScoringEntry ){
				EuroFinalScoringEntry euroEntry = (EuroFinalScoringEntry)entry;
				euroEntry.setStationPoints( 4 * p.getStations().stationsLeft() );
			}
		}
	}
	
	protected int calcExpansionScore( FinalScoringEntry entry ){
	

		int rInt = 0;
		EuroFinalScoringEntry euroEntry;
		if ( entry instanceof EuroFinalScoringEntry ){
			euroEntry = (EuroFinalScoringEntry)entry;
			rInt = euroEntry.getStationPoints();
		}			
		return rInt;
	}
	
}
