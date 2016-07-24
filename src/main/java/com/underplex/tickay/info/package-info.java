/**
 * Provides immutable classes representing the game state that can be safely passed to user-defined strategies.
 * <p>
 * These classes aim to provide a complete information on the current game state of Ticket to Ride (America) or Ticket to Ride Europe, except for the record of the current 
 * game being played. (Classes for getting information about the record of the current game being played are contained in another package.)
 * <p>
 * Classes ending in <code>-Info</code> provide common-knowledge information about the game state and can be passed to any player.
 * Classes ending in <code>-InfoMe</code> provide information specific to a player that only the player should have access to.
 * <p>
 * While these classes can be subclassed and the subclasses may be made mutable, it shouldn't matter, as the simulator specifically never uses any
 * objects created by a strategy class.
 * @author Brandon Irvine
 *
 */
package com.underplex.tickay.info;