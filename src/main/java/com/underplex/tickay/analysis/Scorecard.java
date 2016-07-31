package com.underplex.tickay.analysis;

import java.text.DecimalFormat;


/**
 * Represents the summary statistics for a single strategy class from a single series of simulations.
 * @author Brandon Irvine
 *
 */
public class Scorecard {
	
	private final String strategy; // name of strategy
	private final int wins; // number of games where at least one player playing the strategy won
	private final double avgScore; // average score among all games
	private final double sd; // standard deviation of scores
	private final int gamesPresent; // number of games where the strategy was present
	private final double percentWin;

	/**
	 * @param strategy
	 * @param wins
	 * @param avgScore
	 * @param sd
	 * @param gamesPresent
	 */
	public Scorecard(String strategy, int wins, double avgScore, double sd,
			int gamesPresent) {
		
		this.strategy = strategy;
		this.wins = wins;
		this.avgScore = avgScore;
		this.sd = sd;
		this.gamesPresent = gamesPresent;
		this.percentWin = (double)wins/(double)gamesPresent;

	}

	public void printOut(){
		DecimalFormat percFormatter = new DecimalFormat("%##.#");
		DecimalFormat intFormatter = new DecimalFormat("##########");
		
		DecimalFormat formatter = new DecimalFormat("###.#");
		String sPercentWin = percFormatter.format(this.percentWin);
		String sWins = intFormatter.format(this.wins);
		String sGamesPresent = intFormatter.format(this.gamesPresent);
		String sSD = formatter.format(this.sd);
		String sAvgScore = formatter.format(this.avgScore);
		
		System.out.println("Results for " + strategy +":");
		System.out.println("Games won percentage: " + sPercentWin + " (" + sWins + "/" + sGamesPresent +")");
		System.out.println("Average score: " + sAvgScore );
		System.out.println("Stand. Dev.: " + sSD);
		System.out.println();
	}
	
	public String getStrategy() {
		return strategy;
	}
	
	public int getGamesPresent() {
		return gamesPresent;
	}


	public int getWins() {
		return wins;
	}
	
	public double getPercentWin() {
		return percentWin;
	}
	
	public double getAvgScore() {
		return avgScore;
	}
	
	public double getSd() {
		return sd;
	}
	
}
