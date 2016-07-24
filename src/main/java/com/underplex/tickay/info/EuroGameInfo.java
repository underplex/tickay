package com.underplex.tickay.info;

import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.jaxbinfo.EuroGameEntryInfo;

/**
* Immutable wrapper for the game currently being played.
 * <p>
 * Many of the most important functions of this are taken from  
 * {@link GameInfo GameInfo}.
 * @author Brandon Irvine
 *
 */
public class EuroGameInfo extends GameInfo {

	private EuroGame euroSource;

	/**
	 * Constructor for simulator.
	 * @param source
	 */
	public EuroGameInfo(EuroGame source) {
		super(source);
		this.euroSource = source;
	}

	/**
	 * Returns immutable game entry representing record of the game.
	 * 
	 */
	public EuroGameEntryInfo getGameEntry() {
		return this.euroSource.getGameEntry().info();
	}
	

}
