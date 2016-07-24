package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.jaxb.PlayerType;

/**
 * Abstract parent for interface that is an immutable wrapper for a class representing a
 * the record of a play made in the game.
 * <p>
 * This isn't the same as the play itself (which can function as an option) but
 * as a record (as if written down) of the play that actually occurred.
 * 
 * @author Brandon Irvine
 *
 */
public abstract interface PlayEntryInfo {

	/**
	 * Returns the type of the play that the implementing class represents.
	 */
	PlayType getPlayType();

	/**
	 * Returns the player that the implementing class represents using the player's seating identity, his <code>PlayerType</code>.
	 */
	PlayerType getPlayer();
}
