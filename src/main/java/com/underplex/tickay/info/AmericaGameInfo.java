package com.underplex.tickay.info;

import com.underplex.tickay.game.AmericaGame;
import com.underplex.tickay.jaxbinfo.AmericaGameEntryInfo;

/**
 * Immutable wrapper for the game currently being played.
 * <p>
 * Many of the most important functions of this are taken from  
 * {@link GameInfo GameInfo}.
 *
 * @author Brandon Irvine
 *
 */
public class AmericaGameInfo extends GameInfo {

	private AmericaGame americaSource;

	/**
	 * Constructor used by simulator.
	 */
	public AmericaGameInfo(AmericaGame source) {
		super(source);
		this.americaSource = source;
	}


	/**
	 * Returns immutable game entry representing record of the game.
	 * 
	 */
	public AmericaGameEntryInfo getGameEntry() {
		return this.americaSource.getGameEntry().info();
	}
	

}
