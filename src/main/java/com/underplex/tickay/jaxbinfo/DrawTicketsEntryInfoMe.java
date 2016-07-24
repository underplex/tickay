
package com.underplex.tickay.jaxbinfo;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.jaxb.DrawTicketsEntry;

/**
 * Immutable wrapper for entry recording the drawing and returning of tickets from/to the ticket deck.
 * <p>
 * This class allows access to info exclusively available in game to a single player.
 * @author Brandon Irvine
 */
public class DrawTicketsEntryInfoMe extends DrawTicketsEntryInfo {

    public DrawTicketsEntryInfoMe(DrawTicketsEntry source) {
        super(source);
    }

    /**
     * Returns the tickets kept.
     */
	public List<String> getKeptTickets() {
		return new ArrayList<>( source.getKeptTicket() );
	}

	/**
	 * Returns the tickets returned to the underside of the deck, in the order returned.
	 */
	public List<String> getReturnedTickets() {
		return new ArrayList<>( source.getReturnedTicket() );
	}
}
