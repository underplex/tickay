package com.underplex.tickay.info;

import com.underplex.tickay.game.Route;
import com.underplex.tickay.jaxb.TrainType;

/**
 * Immutable wrapper for a route in the game.
 * @author Brandon Irvine
 *
 */
public class RouteInfo {

	private Route source;

	public RouteInfo(Route source) {
		this.source = source;
	}

	/**
	 * Returns a short label for this route.
	 */
	public String toShortLabelString() {
		return source.toShortLabelString();
	}

	public String toString() {
		return source.toString();
	}

	/**
	 * Returns number of locos required to build this route.
	 */
	public int getLocos() {
		return source.getLocos();
	}

	/**
	 * Returns total length of this route, including locos.
	 */
	public int getLength() {
		return source.getLength();
	}

	/**
	 * Returns the color that this is, or <code>TrainType.GRAY</code> if it's gray.
	 */
	public TrainType getColor() {
		return source.getColor();
	}

	/**
	 * Returns <code>true<code> if this is a tunnel.
	 */
	public boolean isTunnel() {
		return source.isTunnel();
	}

	/**
	 * Returns the route that is paired with this one on the board, or <code>null</code> if there isn't one.
	 */
	public RouteInfo getAlternate() {
		return source.getAlternate().info();
	}
	
	/**
	 * Returns the player who has built this route, or <code>null</code> if it's unbuilt.
	 */
	public PlayerInfo getBuilder() {
		return source.getBuilder().info();
	}

	/**
	 * Returns cities this route connects.
	 */
	public CityPairInfo getCities() {
		return source.getCities().info();
	}

	/**
	 * Return <code>true</code> if this route has been built.
	 */
	public boolean isBuilt() {
		return source.isBuilt();
	}
	
}
