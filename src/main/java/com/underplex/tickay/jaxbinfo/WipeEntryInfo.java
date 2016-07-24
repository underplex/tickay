
package com.underplex.tickay.jaxbinfo;

import java.util.List;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.jaxb.WipeEntry;

/**
* Immutable wrapper for entry recording the replacement of all the face-up cards (a "wipe").
* @author Brandon Irvine
*/
public class WipeEntryInfo {

    private WipeEntry source;

    public WipeEntryInfo(WipeEntry source) {
        this.source = source;
    }

    /**
     * List of all the cards that are turned up to replace the old cards.
     * @return
     */
	public List<DeckDrawEntryInfo> getFaceUpReplacement() {
		return InfoWrapper.wrapToList( source.getFaceUpReplacement() );
	}

}
