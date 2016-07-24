package com.underplex.tickay.info;

import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.player.TicketHandManager;

/**
 * Represents information about ticket hand exclusive to the player.
 * @author Brandon Irvine
 *
 */
public class TicketHandManagerInfoMe {
	
	private TicketHandManager source;

	public TicketHandManagerInfoMe(TicketHandManager source) {
		this.source = source;
	}

	/**
	 * Returns number of ticket cards in ticket hand for the player.
	 */
	public int size(){
		return this.source.getTickets().size();
	}
	
	/**
	 * Returns the player this hand belongs to.
	 */
	public PlayerInfoMe getPlayer(){
		return this.source.getPlayer().myInfo();
	}

	/**
	 * Returns tickets in this hand.
	 * <p>
	 * Changes to the <code>Set</code> returned won't change the ticket helds.
	 */
	public Set<TicketInfo> getTickets() {
		return InfoWrapper.wrapToSet( source.getTickets() );
	} 
	
	
}
