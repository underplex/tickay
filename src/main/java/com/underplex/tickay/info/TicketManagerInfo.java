package com.underplex.tickay.info;

import com.underplex.tickay.game.TicketManager;

/**
 * Immutable wrapper for the class that manages tickets for a game.
 * @author Brandon Irvine
 */
public class TicketManagerInfo {
	
	private TicketManager source;

	/**
	 * Constructor used by simulator.
	 */
	public TicketManagerInfo(TicketManager source) {
		this.source = source;
	}

	/**
	 * Returns the game the wrapped class manages tickets for.
	 */
	public GameInfo getGame() {
		return source.getGame().info();
	}

	/**
	 * Returns the number of cards in the ticket deck.
	 */
	public int getTicketDeckSize() {
		return source.getShorts().size();
	}

	/**
	 * Returns the number of cards set aside from the game in "oblivion".
	 * <p>
	 * In Ticket to Ride Europe, for example, the first ticket discards are set aside and never used again in the game.
	 */
	public int getSetAsideSize() {
		return source.getOblivion().size();
	}

	
	
	
}
