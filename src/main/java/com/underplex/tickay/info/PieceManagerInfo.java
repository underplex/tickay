package com.underplex.tickay.info;

import com.underplex.tickay.player.PieceManager;

/**
 * Immutable wrapper of piece manager for player.
 * <p>
 * The piece manager controls the train pieces player use to claim routes.
 * (These are called "pieces" to differentiate them from "trains".)
 * @author Brandon Irvine
 *
 */
public class PieceManagerInfo {

	private PieceManager source;

	/**
	 * Constructor used by the simulator.
	 */
	public PieceManagerInfo(PieceManager source){
		this.source = source;
	}
	
	/**
	 * Returns number of pieces the player has.
	 */
	public int count() {
		return source.count();
	}

	/**
	 * Returns the player who controls the pieces represented by this piece manager.
	 */
	public PlayerInfo getPlayer() {
		return source.getPlayer().info();
	}

	/**
	 * Returns true if the current number of pieces for the player would end the game.
	 */
	public boolean endsGame() {
		return source.endsGame();
	}
}
