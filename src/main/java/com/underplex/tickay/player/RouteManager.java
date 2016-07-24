package com.underplex.tickay.player;

import java.util.HashSet;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.info.RouteManagerInfo;

/**
 * Route manager for a player. Manages the routes the player owns/controls.
 * 
 * @author irvin_000
 *
 */
@SuppressWarnings("serial")
public class RouteManager extends HashSet<Route> implements
		InfoSource<RouteManagerInfo> {

	private Player player;
	private RouteManagerInfo info;

	public RouteManager(Player player) {
		this.player = player;
		this.info = new RouteManagerInfo(this);
	}

	public RouteManagerInfo info() {
		return this.info;
	}

	public void addRoute(Route route) {
		this.add(route);
		player.getNetworks().addRoute(route);
	}

	public Player getPlayer() {
		return player;
	}
}
