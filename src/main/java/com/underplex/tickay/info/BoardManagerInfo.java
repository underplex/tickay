package com.underplex.tickay.info;

import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.game.BoardManager;

/**
 * Immutable wrapper for the board of a single game.
 * 
 * @author Brandon Irvine
 *
 */
public class BoardManagerInfo {

	private final BoardManager source;

	/**
	 * Constructor for simulator's use.
	 * 
	 * @param source
	 *            BoardManager represented by this wrapper.
	 */
	public BoardManagerInfo(BoardManager source) {
		this.source = source;
	}

	/**
	 * Returns all cities on the board that don't have a station currently on
	 * them.
	 */
	public Set<CityInfo> openCities() {
		return InfoWrapper.wrapToSet(source.openCities());
	}

	/**
	 * Returns all cities on the board.
	 */
	public Set<CityInfo> getCities() {
		return InfoWrapper.wrapToSet(source.getCities());
	}

	/**
	 * Returns all routes on the board.
	 */
	public Set<RouteInfo> getRoutes() {
		return InfoWrapper.wrapToSet(source.getRoutes());
	}

	/**
	 * Returns set of routes that <code>player</code> can legally build.
	 * <p>
	 * This doesn't filter for whether the player can afford to built the route, only that he is legally able to do so.
	 * <p>
	 * If a route isn't already built by another player but it isn't buildable by <code>player</code>, the route is probably an alternate of another
	 * route and either the alternate route has already been built by <code>player</code> or the number of player in the game prevents alternates from being built. 
	 */
	public Set<RouteInfo> buildableRoutes(PlayerInfo player) {
		return source.buildableRoutes(player);
	}	

}
