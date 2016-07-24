package com.underplex.tickay.info;

import com.underplex.tickay.player.EuroPlayer;

/**
 * Immutable wrapper for class representing player of a Ticket to Ride Europe game.
 * @author Brandon Irvine
 *
 */
public class EuroPlayerInfo extends PlayerInfo {

	private EuroPlayer euroSource;

	/**
	 * Returns class instance that manages this player's stations.
	 */
	public StationManagerInfo getStationManager() {
		return this.euroSource.getStations().info();
	}
	
	public EuroPlayerInfo(EuroPlayer source) {
		super(source);
		this.euroSource = source;
	}
	
	/**
	 * Returns the Ticket to Ride Europe game being played by this player.
	 */
	public EuroGameInfo getGame() {
		return euroSource.getGame().info();
	}

}
