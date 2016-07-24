
package com.underplex.tickay.jaxbinfo;

import java.util.List;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.jaxb.EuroGameEntry;

/**
 * Immutable wrapper for entry recording of an entire game of Ticket to Ride Europe.
 * @author Brandon Irvine
 *
 */
public class EuroGameEntryInfo {

    protected EuroGameEntry source;

    public EuroGameEntryInfo(EuroGameEntry source) {
        this.source = source;
    }

    /**
     * Returns entries recording the choices made to extend stations over routes at the end of the game.
     */
	public List<StationChoiceEntryInfo> getStationChoices() {
		return InfoWrapper.wrapToList(source.getStationChoice());
	}

    /**
     * Returns entries recording the discards at the beginning of the game.
     */
	public List<FirstDiscardEntryInfo> getFirstDiscards() {
		return InfoWrapper.wrapToList( source.getFirstDiscard() );
	}

    /**
     * Returns entries recording the turns during the game.
     */
	public List<TurnEntryInfo> getTurns() {
		return InfoWrapper.wrapToList( source.getTurn() );
	}

}
