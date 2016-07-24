/**
 * Provides immutable classes that can be safely passed to user-defined strategies.
 * <p>
 * These classes aim to provide a complete record of a game of Ticket to Ride (America) or Ticket to Ride Europe; "complete" here means the entire game, down to every
 * move of a card from one place to another, should be replicable by reading the XML element that contains the game record (EuroGameEntry or AmericaGameEntry).
 * <p>
 * Classes ending in <code>-Info</code> provide common-knowledge information about the game state and can be passed to any player.
 * Classes ending in <code>-InfoMe</code> provide information specific to a player that only the player should have access to.
 * <p>
 * While these classes can be subclassed and the subclasses may be made mutable, it shouldn't matter, as the simulator specifically never uses any
 * objects created by a strategy class.
 * @author Brandon Irvine
 *
 */
package com.underplex.tickay.jaxbinfo;