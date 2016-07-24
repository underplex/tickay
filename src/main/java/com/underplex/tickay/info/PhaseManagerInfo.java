package com.underplex.tickay.info;

import com.underplex.tickay.game.PhaseManager;
import com.underplex.tickay.game.PhaseType;

/**
 * Immutable wrapper for phase manager of the game.
 * <p>
 * The phase manager controls and records the phase of the game.
 * 
 * @author Brandon Irvine
 *
 */
public class PhaseManagerInfo {

	private final PhaseManager source;

	/**
	 * Constructor that uses the mutable object as source of information.
	 */
	public PhaseManagerInfo(PhaseManager source) {
		this.source = source;
	}

	/**
	 * Returns the current phase of the game.
	 */
	public PhaseType getPhase() {
		return source.getPhase();
	}

	/**
	 * Returns <code>true</code> iff player are playing their last turns now.
	 */
	public boolean isLastTurn() {
		return source.isLastTurn();
	}

	/**
	 * Returns the game this phase manager belongs to.
	 */
	public GameInfo getGame() {
		return source.getGame().info();
	}

	public String toString() {
		return source.toString();
	}

	/**
	 * Returns turn number if the phase is one where there are turns.
	 * <p>
	 * During most of the game, player are taking turns, but in some special expansions or parts there might be no clear concept of "turn", in which
	 * case this returns 0. For example, when player are choosing what their stations count for at the end of a Europe game, the turn number is 0.
	 */
	public int getTurn() {
		return source.getTurn();
	}
}
