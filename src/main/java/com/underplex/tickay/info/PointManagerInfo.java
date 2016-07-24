package com.underplex.tickay.info;

import com.underplex.tickay.player.PointManager;

/**
 * Immutable wrapper for the point manager.
 * @author Brandon Irvine
 *
 */
public class PointManagerInfo {

	private PointManager source;

	/**
	 * Constructor used by the simulator.
	 */
	public PointManagerInfo(PointManager source) {
		this.source = source;
	}

	/**
	 * Returns the current number of points for the player.
	 */
	public int count() {
		return source.count();
	}

	/**
	 * Returns the player this point manager belongs to.
	 */
	public PlayerInfo getPlayer() {
		return source.getPlayer().info();
	}
	
	

}
