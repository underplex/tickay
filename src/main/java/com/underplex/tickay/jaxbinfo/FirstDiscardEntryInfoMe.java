package com.underplex.tickay.jaxbinfo;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.jaxb.FirstDiscardEntry;

/**
 * Immutable wrapper for entry recording the first ticket discards of the game.
 * <p>
 * This class allows access to info exclusively available in game to a single player.
 * <p>
 * Note that the discards don't necessarily return to the ticket deck, depending on the version of the game.
 * @author Brandon Irvine
 */

public class FirstDiscardEntryInfoMe extends FirstDiscardEntryInfo {

	public FirstDiscardEntryInfoMe(FirstDiscardEntry source) {
		super(source);
	}

	/**
	 * Returns tickets kept.
	 */
	public List<String> getKeep() {
		return new ArrayList<>(source.getKeep());
	}

	/**
	 * Returns tickets discarded.
	 * <p>
	 * Note that the discards don't necessarily return to the ticket deck, depending on the version of the game.
	 */
	public List<String> getDiscard() {
		return new ArrayList<>(source.getDiscard());
	}

}
