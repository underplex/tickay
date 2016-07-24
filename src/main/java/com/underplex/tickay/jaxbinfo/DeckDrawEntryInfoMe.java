package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.DeckDrawEntry;
import com.underplex.tickay.jaxb.TrainType;

/**
 * Immutable wrapper for entry recording the drawing of a card from the draw deck.
 * <p>
 * This class allows access to info exclusively available in game to a single player.
 * @author Brandon Irvine
 */
public class DeckDrawEntryInfoMe extends DeckDrawEntryInfo {

	public DeckDrawEntryInfoMe(DeckDrawEntry source) {
		super(source);
	}
	
	/**
	 * Returns the type of the train drawn from the deck, or <code>null</code> if no card to draw.
	 */
	public TrainType getTrainType() {
		return source.getTrainType();
	}
	
}
