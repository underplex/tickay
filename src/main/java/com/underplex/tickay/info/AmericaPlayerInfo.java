package com.underplex.tickay.info;

import com.underplex.tickay.player.AmericaPlayer;

/**
 * Represents a player in the Ticket to Ride America (base) game.
 * @author Brandon Irvine
 *
 */
public class AmericaPlayerInfo extends PlayerInfo {

	private AmericaPlayer americaSource;
	
	public AmericaPlayerInfo(AmericaPlayer source) {
		super(source);
		this.americaSource = source;
	}
	
	/**
	 * Returns game that this player is a part of.
	 */
	public AmericaGameInfo getGame() {
		return americaSource.getGame().info();
	}
}
