package com.underplex.tickay.info;

import com.underplex.tickay.jaxbinfo.EuroGameEntryInfoMe;
import com.underplex.tickay.player.EuroPlayer;

/**
 * Immutable wrapper for class representing the information exclusively available to a player of Ticket to Ride Europe.
 * @author Brandon Irvine
 *
 */
public class EuroPlayerInfoMe extends PlayerInfoMe{

	private EuroPlayer euroSource;
	private EuroGameEntryInfoMe euroGameEntry;
	
	public EuroPlayerInfoMe( EuroPlayer source ) {
		super(source);
		this.euroSource = source;
		this.euroGameEntry = new EuroGameEntryInfoMe( source.getGame().getGameEntry(), source.getPlayerType());
	}

	/**
	 * Returns game this player is playing.
	 */
	public EuroGameInfo getGame() {
		return euroSource.getGame().info();
	}

	/**
	 * Returns station manager of this player.
	 */
	public StationManagerInfo getStations() {
		return euroSource.getStations().info();
	}
	
	/**
	 * Returns game entry info with record of game with info exclusive to this player available.
	 */
	public EuroGameEntryInfoMe getMyGameEntry(){
		return this.euroGameEntry;
	}
	
}
