
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.DeckDrawEntry;

/**
 * Immutable wrapper for entry recording the drawing of a card from the draw deck.
 * @author Brandon Irvine
 */
public class DeckDrawEntryInfo {

    protected DeckDrawEntry source;

    public DeckDrawEntryInfo(DeckDrawEntry source) {
        this.source = source;
    }
	
    /**
     * Returns <code>true</code> iff there were any cards to draw.
     * <p>
     * If this returns <code>false</code>, no card is actually drawn because there are none to draw.
     */
	public boolean isCardToDraw() {
		return source.isCardToDraw();
	}

	/**
	 * Returns <code>true</code> if this drawing was preceded by a reshuffle.
	 * <p>
	 * In the game, reshuffles occur when a player needs to draw from the draw deck (face-down cards)
	 * but there are only cards in the discard deck (face-up cards discarded as payment or in a wipe).
	 * <p>
	 * In reshuffling, the entire discard deck is reshuffled and placed face-down to create the new draw deck.
	 * @return
	 */
	public boolean isReshuffle() {
		return source.isReshuffle();
	}
}
