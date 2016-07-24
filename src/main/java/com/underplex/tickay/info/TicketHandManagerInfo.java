package com.underplex.tickay.info;

import com.underplex.tickay.player.TicketHandManager;

/**
 * Immutable wrapper for ticket manager.
 * <p>
 * Note that this is for common knowledge in the game that all player are allowed.
 * @author Brandon Irvine
 *
 */
public class TicketHandManagerInfo {
	
	private TicketHandManager source;

	public TicketHandManagerInfo(TicketHandManager source) {
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
	public PlayerInfo getPlayer(){
		return this.source.getPlayer().info();
	}
	
}
