package com.underplex.tickay.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.underplex.tickay.jaxb.AmericaGameEntry;
import com.underplex.tickay.jaxb.FinalScoringEntry;
import com.underplex.tickay.jaxb.GameEntry;
import com.underplex.tickay.jaxb.PlayerDefinitionEntry;

/**
 * Utility class for analyzing simulation logs.
 * @author Brandon Irvine
 *
 */
public class Scorer {

	private Scorer(){
		// don't instatiate
	}

	/**
	 * Return Set of Strings representing all of the classes used for strategies among GameEntry elements.
	 * @param entries
	 * @return
	 */
	public static Set<String> findStrategyNames(Collection<? extends GameEntry> entries){
		Set<String> names = new HashSet<>();
		for (GameEntry ge : entries)
			for (PlayerDefinitionEntry pde : ge.getSetup().getPlayerDefinition())
				names.add(pde.getStrategyIdentifier());
		return names;
	}


	/**
	 * Return Map of scorecards
	 */
	public static Set<Scorecard> findAmScoreCards(Collection<AmericaGameEntry> entries){
		
		Map<String,List<Double>> percentages = new HashMap<>();
		Map<String,List<Integer>> scores = new HashMap<>();
		Set<String> names = findStrategyNames(entries);
		Map<String,Integer> present = new HashMap<>();
		Map<String,Integer> won = new HashMap<>();
		
		for (String n : names){
			percentages.put(n,new ArrayList<Double>());
			scores.put(n,new ArrayList<Integer>());
			present.put(n, 0);
			won.put(n,0);
		}

		for (AmericaGameEntry entry : entries){
			double winner = (double)findWinningScore(entry);
			Set<String> presentGame = new HashSet<>();
			Set<String> winning = new HashSet<>();
			
			// adding the names to a set prevents us from double counting games where the strategy is played by more than one player
			// similarly, adding the names to a set of winners prevents us from double counting games where the same strategy tied itself
			for (FinalScoringEntry fse: entry.getFinalScoring()){
				String name = fse.getStrategyClass();
				// divide score by winning score and add to list of percentages for that name
				percentages.get(name).add(winner/(double)fse.getFinalScore());
				// add score to name list
				scores.get(name).add(fse.getFinalScore());
				presentGame.add(name);
				if (fse.isWinner())
						winning.add(name);
			}
			
			// increment the number of games present for strategies in this game
			for (String p : presentGame)
				present.put(p, present.get(p) + 1);
			
			// increment the games won for any winning strategies in this game
			for (String w : winning)
				won.put(w, won.get(w) + 1);
			
		}
		
		
		// find all the names

		
		Set<Scorecard> scorecards = new HashSet<>();
		for (String n : names){
			
			List<Double> doubleScores = new ArrayList<>();
			
			int num = scores.get(n).size();
			// there's no good way to both unbox and cast Integer to primitive double[] array to be used by StandardDeviation...
			double[] rawScores = new double[num];
			for (int i = 0 ; i < num ; i++){
				rawScores[i] = (double)scores.get(n).get(i);
			}
			double mean = StatUtils.mean(rawScores);
			StandardDeviation sdCalculator = new StandardDeviation();
			
			double sd = sdCalculator.evaluate(rawScores,mean);

			scorecards.add( new Scorecard(n, present.get(n), mean, sd, won.get(n)));
			
		}
		
		return scorecards;
		
	}
	
	/**
	 * Returns number representing highest score in the game represented by entry.
	 * 
	 * Returns 0 if entry game is null or if there are no final scoring entries associated with the game.
	 */
	public static int findWinningScore(AmericaGameEntry entry){
		int winning = 0;
		if (entry != null)
			for (FinalScoringEntry e : entry.getFinalScoring())
				if (e.getFinalScore() > winning)
					winning = e.getFinalScore();
		
		
		return winning;
	}
	
	
}
