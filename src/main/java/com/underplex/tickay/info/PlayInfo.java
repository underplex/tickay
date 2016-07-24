package com.underplex.tickay.info;

import com.underplex.tickay.jaxb.PlayType;

/**
 * An implementing class will be an immutable wrapper for a class representing a play in the game.
 * @author Brandon Irvine
 *
 */
public interface PlayInfo {
  
	/**
	 * Returns the type of the play that the implementing class represents.
	 */
  PlayType getPlayType();
  
}
