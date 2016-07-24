package com.underplex.tickay.info;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.game.TrainManager;
import com.underplex.tickay.jaxb.TrainType;

/**
 * Immutable wrapper for the train card manager for the game, including the deck, discard, and face-ups.
 * @author Brandon Irvine
 *
 */
public class TrainManagerInfo {

	private TrainManager source;
	
	public TrainManagerInfo(TrainManager source){
		this.source = source;
	}

	/**
	 * Returns <code>List</code> with each element representing 1 card face up.
	 * <p>
	 * Changes in the returned list won't change the actual face-ups 
	 */
	public List<TrainType> getFaceUps() {
		return new ArrayList<TrainType>( source.getFaceUps() );
	}

	/**
	 * Returns <code>true</code> if there are no more cards in deck or discard.
	 */
	public boolean isEmpty() {
		return source.isEmpty();
	}

	/**
	 * Returns <code>true</code> if there are no cards face-up.
	 */
	public boolean hasNoFaceUps() {
		return source.hasNoFaceUps();
	}
	/**
	 * Returns number of cards is both deck and discard.
	 */
	public int drawSize() {
		return source.drawSize();
	}

	/**
	 * Returns number of cards in the deck.
	 */
	public int deckSize() {
		return source.deckSize();
	}

	/**
	 * Returns number of cards in discard.
	 */
	public int discardSize() {
		return source.discardSize();
	}
	
}
