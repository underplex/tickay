package com.underplex.tickay.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.game.City;
import com.underplex.tickay.game.CityPair;
import com.underplex.tickay.game.Path;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.info.NetworkManagerInfo;

/**
 * Represents a player's various networks, that is, connected routes that
 * connect one city to another.
 * 
 * @author irvin_000
 *
 */
public class NetworkManager implements InfoSource<NetworkManagerInfo> {

	private Player player;
	private Set<Network> networks;
	private NetworkManagerInfo info;

	public NetworkManager(Player player) {
		this.player = player;
		this.networks = new HashSet<>();
		this.info = new NetworkManagerInfo(this);

	}
	
	public NetworkManagerInfo info(){
		return this.info;
	}

	public void addRoute( Route route ) {

		CityPair pair = route.getCities();
		List<Network> containers = new ArrayList<>();

		// find all the networks that contain a city the route ends in
		for (Network net : networks)
			if (net.contains(pair.getCity1()) || net.contains(pair.getCity2()))
				containers.add(net);

		switch ( containers.size() ) {
		case 0: // no existing networks have a city that would connect to the route
			this.networks.add( new Network(route, this.player ) );
			break;
		case 1: // only one existing network has a city that would connect to the route
			containers.get(0).addBuiltRoute( route );
			break;
		case 2: // two networks would contain the route, so they will be merged and the route included
			Network addTo = containers.get(0);
			addTo.addBuiltRoute(route); // add the route to route 1
			Network superNet = new Network( containers.get(0), containers.get(1) );
			networks.remove( containers.get(0) );
			networks.remove( containers.get(1) );
			networks.add(superNet);
			break;
		} // end switch
	} // end method

	public void addStationDecision(City station, Route route) {

		// this method isn't very DRY but we are distinguishing in Networks
		// between adding routes based on stations vs. built routes
		CityPair pair = route.getCities();
		List<Network> containers = new ArrayList<>(); // networks that would connect to this station/route

		// find all the networks that have this
		for ( Network net : networks )
			if ( net.contains( pair.getCity1() ) || net.contains( pair.getCity2() ) )
				containers.add(net);
		
		// logically, there can only be 0, 1, or 2 networks that this would connect
		switch ( containers.size() ) {
		case 0:
			this.networks.add( new Network( station, route, this.player ) );
			break;
		case 1:
			containers.get(0).addStationRoute( station, route );
		case 2:
			containers.get(0).addStationRoute( station, route );
			Network superNet = new Network( containers.get(0), containers.get(1) );
			networks.remove( containers.get(0) );
			networks.remove( containers.get(1) );
			networks.add( superNet );
		} // end switch

	}

	/**
	 * Returns all Paths that player has.
	 * 
	 * Returns empty set if there are no paths associated with player.
	 * 
	 * Paths do not use or include stations or the routes that stations cover.
	 */
	public Set<Path> paths() {
		Set<Path> rSet = new HashSet<>();
		for ( Network network : this.networks )
			rSet.addAll( network.paths() );
		return rSet;
	} // end method

	/**
	 * Return true if the player can legally connect two cities using any of his
	 * networks.
	 */
	public boolean canConnect(CityPair cityPair) {
		boolean rValue = false;

		for (Network network : networks)

			if (network.canConnect(cityPair))
				rValue = true;

		return rValue;
	} // end method

	/**
	 * Return true if the player can legally connect two cities using any of his
	 * networks.
	 */
	public boolean canConnect(City city1, City city2) {
		boolean rValue = false;

		for (Network network : networks)
			if (network.canConnect( new CityPair(city1,city2)))
				rValue = true;

		return rValue;
	} // end method
	
	public Set<Network> getAll(){
		return new HashSet<>( this.networks );
	}
	
}
