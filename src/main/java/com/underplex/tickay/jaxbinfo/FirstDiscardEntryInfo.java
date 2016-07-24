
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.FirstDiscardEntry;
import com.underplex.tickay.jaxb.PlayerType;

/**
 * Immutable wrapper for entry recording the first ticket discards of the game.
 * <p>
 * Note that the discards don't necessarily return to the ticket deck, depending on the version of the game.
 * @author Brandon Irvine
 *
 */
public class FirstDiscardEntryInfo {

    protected FirstDiscardEntry source;

    public FirstDiscardEntryInfo(FirstDiscardEntry source) {
        this.source = source;
    }

    /**
     * Returns type of the player making the discards.
     */
	public PlayerType getPlayer() {
		return source.getPlayer();
	}

    /**
     * Returns number of tickets kept.
     */
	public int getKeepSize() {
		return source.getKeep().size();
	}

	/**
     * Returns number of tickets discarded.
     * <p>
     * Note that the discards don't necessarily return to the ticket deck, depending on the version of the game.
     */
	public int getDiscardSize() {
		return source.getDiscard().size();
	}

}
