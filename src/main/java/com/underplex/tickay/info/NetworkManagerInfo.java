package com.underplex.tickay.info;

import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.player.NetworkManager;

/**
 * Immutable wrapper for a network manager for one player.
 * @author Brandon Irvine
 *
 */
public class NetworkManagerInfo {

	private NetworkManager source;

	public NetworkManagerInfo(NetworkManager source) {
		this.source = source;
	}

	/**
	 * Returns <code>true</code> iff the pair of cities are connected by any of the player's networks.
	 */
	public boolean canConnect(CityPairInfo cityPair) {
		boolean rBool = false;
		
		// iterate through nets, make true if any can connect
		for (NetworkInfo net : this.getAll() )
			if ( net.canConnect(cityPair ) )
				rBool = true;
		
		return rBool;
	}

	/**
	 * Returns <code>true</code> iff the two cities are connected by any of the player's networks.
	 */
	public boolean canConnect(CityInfo city1, CityInfo city2) {
		return this.canConnect( new CityPairInfo(city1,city2));
	}
	
	/**
	 * Returns <code>Set</code> representing all the networks this player owns.
	 */
	public Set<NetworkInfo> getAll() {
		return InfoWrapper.wrapToSet( source.getAll() );
	}

	public String toString() {
		return source.toString();
	}
	
	
	
	
}
