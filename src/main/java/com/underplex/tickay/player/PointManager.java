package com.underplex.tickay.player;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.PointManagerInfo;

public class PointManager implements InfoSource<PointManagerInfo> {

	private Player player;
	private int points;
	private PointManagerInfo info;

	public PointManager(Player player) {
		this.player = player;
		this.points = 0;
		this.info = new PointManagerInfo(this);
	}

	/**
	 * Returns number of points the player currently has.
	 */
	public int count() {
		return points;
	}

	public void addPoints( int add ){
		this.points += add;
	}
	
	public Player getPlayer() {
		return player;
	}

	public PointManagerInfo info() {
		return this.info;
	}

}
