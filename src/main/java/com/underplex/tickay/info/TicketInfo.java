package com.underplex.tickay.info;

import com.underplex.tickay.game.LengthType;
import com.underplex.tickay.game.Ticket;

/**
 * Immutable wrapper for a ticket.
 * @author Brandon Irvine
 *
 */
public class TicketInfo {

	private Ticket source;

	/**
	 * Constructor using mutable instance as source for information.
	 * @param source
	 */
	public TicketInfo(Ticket source) {
		this.source = source;
	}

	public String toString() {
		return source.toString();
	}

	/**
	 * Returns points this ticket is worth.
	 */
	public int getPoints() {
		return source.getPoints();
	}

	/**
	 * Returns cities that are joined by this ticket.
	 */
	public CityPairInfo getCityPair() {
		return source.getCityPair().info();
	}

	/**
	 * Returns type of ticket, either short or long.
	 */
	public LengthType getLengthType() {
		return source.getLengthType();
	}

}