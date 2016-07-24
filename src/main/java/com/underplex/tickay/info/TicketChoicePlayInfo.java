package com.underplex.tickay.info;

import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.play.TicketChoicePlay;

/**
 * Immutable wrapper for a play option that would return or keep specific subsets of cards as follow up to a Ticket draw play.
 * @author Brandon Irvine
 *
 */
public class TicketChoicePlayInfo implements PlayInfo {

	protected TicketChoicePlay source;

	/**
	 * Constructor using original mutable instance as source of information.
	 */
	public TicketChoicePlayInfo(TicketChoicePlay source) {
		this.source = source;
	}

	public PlayType getPlayType() {
		return PlayType.DRAW_TICKETS;
	}

	/**
	 * Returns TicketInfo instances representing cards the player would keep with this play.
	 */	
	public Set<TicketInfo> getKeep(){
		return InfoWrapper.wrapToSet( source.getKeep() );
	}
	
	/**
	 * Returns TicketInfo instances representing cards the player would return with this play.
	 */
	public Set<TicketInfo> getPutBack(){
		return InfoWrapper.wrapToSet( source.getPutBack() );
	}

}
