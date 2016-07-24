package com.underplex.tickay.info;

import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.player.Network;

/**
 * Immutable wrapper for a network owned by one player.
 * <p>
 * A network is a grouping of connected routes and cities so that if a ticket has two cities in the same network, that ticket is complete.
 * <p>
 * Cities and routes that are connected to the network by claimed (built) routes are not specially treated. Special methods exist, however, for
 * getting the cities and routes *only* connected because of a station placement.
 * <p>
 * @author Brandon Irvine
 *
 */
public class NetworkInfo {

	private Network source;

	public NetworkInfo(Network source) {
		this.source = source;
	}

	public String toString() {
		return source.toString();
	}

	/**
	 * Returns owner of this network.
	 */
	public PlayerInfo getPlayer() {
		return source.getPlayer().info();
	}

	/**
	 * Returns cities connected by the regular part of this network.
	 */
	public Set<CityInfo> getCities() {
		return InfoWrapper.wrapToSet( source.getCities() );
	}

	/**
	 * Returns routes connected by the regular part of this network.
	 */
	public Set<RouteInfo> getRoutes() {
		return InfoWrapper.wrapToSet( source.getRoutes() );
	}

	/**
	 * Returns cities in this network (connected regularly or by station) that have stations.
	 * <p>
	 * These cities might be either regularly connected or only connected by station.
	 */
	public Set<CityInfo> getStations() {
		return InfoWrapper.wrapToSet( source.getStations() );
	}

	/**
	 * Returns routes in this network connected only by stations.
	 */
	public Set<RouteInfo> getStationRoutes() {
		return InfoWrapper.wrapToSet( source.getStationRoutes() );
	}

	/**
	 * Returns cities in this network connected only by stations.
	 */
	public Set<CityInfo> getCitiesCovered() {
		return InfoWrapper.wrapToSet( source.getCitiesCovered() );
	}

	/**
	 * Returns <code>true</code> iff the <code>city</code> is connected.
	 */
	public boolean contains(CityInfo city) {
		return source.contains(city);
	}
	/**
	 * Returns <code>true</code> iff the cities in <code>cityPair</code> are both connected.
	 */
	public boolean canConnect(CityPairInfo cityPair) {
		return source.canConnect(cityPair);
	}	
}
