
package com.underplex.tickay.jaxbinfo;

import java.util.List;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.jaxb.GameEntry;

/**
 * Immutable wrapper for entry recording of an entire game of Ticket to Ride America (aka base version).
 * @author Brandon Irvine
 *
 */
public class AmericaGameEntryInfo {

    protected GameEntry source;

    /**
     * Constructor used by simulator.
     */
    public AmericaGameEntryInfo(GameEntry source) {
        this.source = source;
    }
    
    /**
     * Returns entries recording the first discards.
     */
	public List<FirstDiscardEntryInfo> getFirstDiscards() {
		return InfoWrapper.wrapToList( source.getFirstDiscard() );
	}

	/**
	 * Records entries recording turns.
	 */
	public List<TurnEntryInfo> getTurns() {
		return InfoWrapper.wrapToList( source.getTurn() );
	}

}
