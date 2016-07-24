package com.underplex.tickay.info;

import com.underplex.tickay.jaxbinfo.AmericaGameEntryInfoMe;
import com.underplex.tickay.player.AmericaPlayer;

/**
 * Represents information about a player that is exclusively available to the player in the game.
 * @author Brandon Irvine
 */
public class AmericaPlayerInfoMe extends PlayerInfoMe{

	private AmericaPlayer americaSource;
	private AmericaGameEntryInfoMe americaGameEntry;
	
	public AmericaPlayerInfoMe( AmericaPlayer source ) {
		super(source);
		this.americaSource = source;
		this.americaGameEntry = new AmericaGameEntryInfoMe( source.getGame().getGameEntry(), source.getPlayerType());
	}

	/**
	 * Returns game this player is a part of.
	 */
	public AmericaGameInfo getGame() {
		return americaSource.getGame().info();
	}
	
	/**
	 * Returns game entry (record) with information available exclusively to the player.
	 */
	public AmericaGameEntryInfoMe getMyGameEntry(){
		return this.americaGameEntry;
	}
	
}
