
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.jaxb.TakeTrainsEntry;

/**
 * Immutable wrapper for entry recording a turn where a player takes train cards.
 * @author Brandon Irvine 
 */
public class TakeTrainsEntryInfo implements PlayEntryInfo {

    protected TakeTrainsEntry source;
    
    /**
     * Constructor used by simulator.
     */
    public TakeTrainsEntryInfo(TakeTrainsEntry source) {
        this.source = source;
    }
    
	public PlayerType getPlayer() {
		return source.getPlayer();
	}

	public PlayType getPlayType() {
		return PlayType.TAKE_TRAINS;
	}
	
	/**
	 * Returns info on first card taken.
	 */
	public SingleTakeEntryInfo getTake1() {
		return source.getTake1().info();
	}

	/**
	 * Returns info on second card taken, if any.
	 */
	public SingleTakeEntryInfo getTake2() {
		return source.getTake2().info();
	}

}
