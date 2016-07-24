
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.SingleTakeEntry;

/**
 * Immutable wrapper for entry recording the taking of a single card. 
 * <p>
 * This class allows access to info exclusively available in game to a single player.
 * @author Brandon Irvine
 */
public class SingleTakeEntryInfoMe extends SingleTakeEntryInfo {

    private SingleTakeEntry source;

    /**
     * Constructor used by simulator.
     * @param source
     */
    public SingleTakeEntryInfoMe(SingleTakeEntry source) {
        super(source);
    }

    /**
     * Returns entry for card taken from deck that shows the actual card gotten by taking form the deck, if any.
     */
    public DeckDrawEntryInfoMe getDeckTaken() {
		return source.getDeckTaken().myInfo();
	}

}
