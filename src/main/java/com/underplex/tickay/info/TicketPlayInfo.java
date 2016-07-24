package com.underplex.tickay.info;

import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.play.TicketPlay;

/**
 * Immutable wrapper for play option to take tickets from deck and keep or discard them.
 * @author Brandon Irvine
 *
 */
public class TicketPlayInfo implements PlayInfo {

	private TicketPlay source;

	public TicketPlayInfo(TicketPlay source) {
		this.source = source;
	}

	public String toString() {
		return source.toString();
	}

	public PlayType getPlayType() {
		return PlayType.DRAW_TICKETS;
	}

}
