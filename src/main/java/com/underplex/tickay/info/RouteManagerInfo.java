package com.underplex.tickay.info;

import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.player.RouteManager;

/**
 * Immutable wrapper for class managing routes built by a player.
 * @author Brandon Irvine
 *
 */
public class RouteManagerInfo {

	private RouteManager source;

	/**
	 * Constructor used by simulator.
	 */
	public RouteManagerInfo(RouteManager source) {
		this.source = source;
	}

	public String toString() {
		return source.toString();
	}
	
	/**
	 * Returns <code>Set</code> representation of all routes the player has built.
	 */
	public Set<RouteInfo> getAll(){
		return InfoWrapper.wrapToSet(source);
	}
	
	/**
	 * Returns player the wrapped class manages the routes for. 
	 */
	public PlayerInfo getPlayer(){
		return source.getPlayer().info();
	}	
	
}
