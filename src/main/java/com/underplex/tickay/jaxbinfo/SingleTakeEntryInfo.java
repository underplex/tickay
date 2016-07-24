
package com.underplex.tickay.jaxbinfo;

import java.util.ArrayList;
import java.util.List;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.jaxb.SingleTakeEntry;
import com.underplex.tickay.jaxb.TrainType;

/**
 * Immutable wrapper for entry recording the taking of a single card.
 * @author Brandon Irvine
 */
public class SingleTakeEntryInfo {

    protected SingleTakeEntry source;

    public SingleTakeEntryInfo(SingleTakeEntry source) {
        this.source = source;
    } 

    /**
     * Returns <code>-Info</code> instance if there was a card drawn from the deck, otherwise <code>null</code>.
     */
	public DeckDrawEntryInfo getDeckTaken() {
		if ( source.getDeckTaken() != null )
			return source.getDeckTaken().info();
		
		return null;
	}
	
	/**
	 * Returns list of face-up cards offered when this card was taken.
	 */
	public List<TrainType> getFaceUpOffered() {
		return new ArrayList<>( source.getFaceUpOffered() );
	}

    /**
     * Returns train taken from face-ups if one was taken from there, otherwise <code>null</code>.
     */
	public TrainType getFaceUpTaken() {
		if ( source.getFaceUpTaken() != null )
			return source.getFaceUpTaken();
		
		return null;
	}

    /**
     * Returns deck draw entry for train added to face-ups if one was taken from there, otherwise <code>null</code>.
     */
	public DeckDrawEntryInfoMe getFaceUpRevealed() {
		if ( source.getFaceUpRevealed() != null )
			return source.getFaceUpRevealed().myInfo();
		
		return null;
	}

	/**
	 * Returns records of any wipes that occurred after the card was taken.
	 */
	public List<WipeEntryInfo> getWipe() {
		return InfoWrapper.wrapToList( source.getWipe() );
	}

}
