package com.underplex.tickay.info;

import com.underplex.tickay.player.TrainHandManager;

/**
 * Immutable wrapper for train hand manager.
 * <p>
 * Note that this is for common knowledge in the game that all player are allowed.
 * <p>
 * "Trains" here means cards with trains on them (not tokens of trains that are placed on the board).
 * @author Brandon Irvine
 *
 */
public class TrainHandManagerInfo {

	private TrainHandManager source;

	/**
	 * Constructor for simulator.
	 */
	public TrainHandManagerInfo(TrainHandManager source) {
		this.source = source;
	}

	/**
	 * Returns player who this hand belongs to.
	 */
	public PlayerInfo getPlayer() {
		return source.getPlayer().info();
	}

	/**
	 * Returns number of cards in this hand.
	 */
	public int size() {
		return source.size();
	}
	
	
	
}
