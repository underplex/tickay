package com.underplex.tickay.info;

import java.util.List;

import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.player.TrainHandManager;

/**
 * Immutable wrapper for train hand manager.
 * <p>
 * Note that this is for secret knowledge in the game that only the player this belongs to can see.
 * <p>
 * "Trains" here means cards with trains on them (not tokens of trains that are placed on the board).
 * @author Brandon Irvine
 *
 */
public class TrainHandManagerInfoMe {

	private TrainHandManager source;

	/**
	 * Constructor for simulator.
	 */
	public TrainHandManagerInfoMe(TrainHandManager source) {
		this.source = source;
	}

	/**
	 * Returns player who this hand belongs to.
	 */
	public PlayerInfoMe getPlayer() {
		return source.getPlayer().myInfo();
	}

	/**
	 * Returns number of cards in this hand.
	 */
	public int size() {
		return source.size();
	}

	/**
	 * Returns number of train cards in hand of <code>type</code>.
	 */
	public int count(TrainType type) {
		return source.count(type);
	}

	/**
	 * Returns list form of the cards in this hand.
	 * <p>
	 * Changes to this list won't affect the actual cards held.
	 */
	public List<TrainType> list() {
		return source.list();
	}	
	
	
	
}
