
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.DrawTicketsEntry;
import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.jaxb.PlayerType;

/**
 * Immutable wrapper for entry recording the drawing and returning of tickets from/to the ticket deck.
 * @author Brandon Irvine
 */
public class DrawTicketsEntryInfo implements PlayEntryInfo {

    protected DrawTicketsEntry source;

    public DrawTicketsEntryInfo(DrawTicketsEntry source) {
        this.source = source;
    }
    
	public PlayerType getPlayer() {
		return source.getPlayer();
	}
    
	public PlayType getPlayType() {
		return PlayType.DRAW_TICKETS;
	}
	
	/**
	 * Returns number of tickets kept by the player.
	 */
	public int getKeptTicketSize() {
		return source.getKeptTicket().size();
	}
	
	/**
	 * Returns number of tickets returned by the player.
	 */
	public int getReturnedTicketSize() {
		return source.getReturnedTicket().size();
	}

	/**
	 * Returns <code>true</code> iff the ticket deck is empty and there are no cards to draw.
	 */
	public boolean isNoTicketsToTake() {
		return source.isNoTicketsToTake();
	}

}
