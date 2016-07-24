package com.underplex.tickay.player; //

import java.util.HashSet;
import java.util.Set;

import com.underplex.infowrap.InfoSource;
import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.game.City;
import com.underplex.tickay.game.CityPair;
import com.underplex.tickay.game.Path;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.info.CityInfo;
import com.underplex.tickay.info.CityPairInfo;
import com.underplex.tickay.info.NetworkInfo;

/**
 * Represents routes built by a single player and the cities they connect.
 * @author irvin_000
 *
 */
public class Network implements InfoSource<NetworkInfo>{

	private final Player player;
	private final Set<City> cities; // cities covered regularly by this network -- NOT using stations
	private final Set<Route> routes; // routes covered regularly by this network -- NOT using stations
	private final Set<City> stations; // cities with stations, no matter if they're covered by the coverage or regularly
	private final Set<Route> stationRoutes; // routes only covered by stations (NOT regularly)
	private final Set<City> citiesCovered; // cities connected by only by station coverage, not by being in network
	private final NetworkInfo info;

	/**
	 * Instantiate this based on a single route that will be the first part of
	 * this network.
	 * 
	 * @param player
	 *            Player that owns this network
	 */
	public Network( Route route, Player player ) {

		this.player = player;
		this.routes = new HashSet<>();
		this.routes.add(route);

		this.citiesCovered = new HashSet<>();
		this.cities = new HashSet<>();

		this.cities.add( route.getCities().getCity1() );
		this.cities.add( route.getCities().getCity2() );

		this.stations = new HashSet<>();
		this.stationRoutes = new HashSet<>();

		this.info = new NetworkInfo(this);
	}

	/**
	 * Constructor for new network based on station route.
	 */
	public Network(City station, Route route, Player player) {
		this.player = player;
		this.routes = new HashSet<>();
		this.cities = new HashSet<>();

		this.stations = new HashSet<>();
		this.stationRoutes = new HashSet<>();
		this.citiesCovered = new HashSet<>();

		this.addStationRoute(station, route);
		this.info = new NetworkInfo(this);
	}

	/**
	 * Constructor for merging two existing networks into one.
	 * <p>
	 * Note that this won't affect the existing networks or remove them from any lists.
	 * @param net1
	 * @param net2
	 */
	public Network( Network net1, Network net2 ){
		this.player = net1.getPlayer();
		this.routes = new HashSet<>();
		this.routes.addAll( net1.getRoutes() );
		this.routes.addAll( net2.getRoutes() );
		
		this.cities = new HashSet<>();
		this.cities.addAll( net1.getCities() );
		this.cities.addAll( net2.getCities() );

		this.citiesCovered = new HashSet<>();
		this.citiesCovered.addAll( net1.getCitiesCovered() );
		this.citiesCovered.addAll( net2.getCitiesCovered() );

		this.stations = new HashSet<>();
		this.stations.addAll( net1.getStations() );
		this.stations.addAll( net2.getStations() );
		
		this.stationRoutes = new HashSet<>();
		this.stationRoutes.addAll( net1.getStationRoutes() );
		this.stationRoutes.addAll( net2.getStationRoutes() );

		this.info = new NetworkInfo(this);
	}
	
	public String toString(){
		return "NETWORK with cities " + this.cities.toString() + " and stations covering " + this.citiesCovered.toString();
	}
	
	public NetworkInfo info(){
		return this.info;
	}

	public Player getPlayer() {
		return player;
	}

	public Set<City> getCities() {
		return cities;
	}

	public Set<Route> getRoutes() {
		return routes;
	}

	public Set<City> getStations() {
		return stations;
	}

	public Set<Route> getStationRoutes() {
		return stationRoutes;
	}

	public Set<City> getCitiesCovered() {
		return citiesCovered;
	}

	/**
	 * Adds a route because it has been built (not because it's a station coverage).
	 */
	public void addBuiltRoute( Route route){
		this.routes.add( route );
		this.cities.add( route.getCities().getCity1() );
		this.cities.add( route.getCities().getCity2() );
		// since the cities are now covered by the regular network, they aren't "covered" anymore
		this.citiesCovered.remove( route.getCities().getCity1());
		this.citiesCovered.remove( route.getCities().getCity2());
	}
	
	/**
	 * Returns true if this contains the specified city.
	 */
	public boolean contains(City city) {
		return (cities.contains(city) || stations.contains(city) || citiesCovered.contains(city));
	}

	/**
	 * Returns true if this contains the specified city.
	 */
	public boolean contains(CityInfo city) {
		boolean rBool = false;
		if ( InfoWrapper.wrapToSet( this.cities ).contains(city) )
			rBool = true;
		if ( InfoWrapper.wrapToSet( this.stations ).contains(city) )
			rBool = true;
		if ( InfoWrapper.wrapToSet( this.citiesCovered ).contains(city) )
			rBool = true;
		
		return rBool;
	}
	
	public void addStationRoute( City station, Route route ) {
		stations.add( station );
		stationRoutes.add( route );
		// add the other pair of the route (not the station) to cities
		if ( !this.cities.contains( route.getCities().getCity1() ) ){
			citiesCovered.add( route.getCities().getCity1() );
		}
		if ( !this.cities.contains( route.getCities().getCity2() ) ){
			citiesCovered.add( route.getCities().getCity2() );
		}
		
	} // end method

	/**
	 * Returns Set of all paths in this network.
	 */
	public Set<Path> paths() {
		Set<Path> rSet = new HashSet<>();
		// boot-strap the recursive call by priming it with single-route Path and every
		// available route on the network
		for ( City city : this.getCities() ){
			rSet.addAll( this.branchingPaths( city, new Path( city, this.player ), new HashSet<>( this.routes ) ) );
		}
		return rSet;
	} // end method
	
	/**
	 * Returns all the Paths that could be created from this network.
	 * <p>
	 * Uses recursion to build branching paths that end only when the running
	 * Set of unused routes has no more routes to take.
	 */
	public Set<Path> branchingPaths( City head, Path working, Set<Route> unused ) {

		Set<Path> rSet = new HashSet<Path>();

		Set<Route> branches = this.connectors( head, unused );
		
		// if there are no places to branch, this is done and the working Path should be returned
		if ( branches.size() == 0 ){
			rSet.add( working );
		} else {
			Path copyOfWorking;
			Set<Route> copyOfUnused;
			City nextHead;
			for ( Route branch : branches ) {
				copyOfWorking = working.copy();
				copyOfWorking.addRoute( branch );
				copyOfUnused = new HashSet<Route>( unused );
				copyOfUnused.remove( branch );
				// get the other city that the branching route connects head to
				nextHead = branch.getCities().getOther( head );
				copyOfWorking.setHead(nextHead);
				rSet.addAll( this.branchingPaths( nextHead, copyOfWorking, copyOfUnused ) );
			}
		}
		
		return rSet;
		
	} // end method

	/**
	 * Returns true if and only if this network contains both cities, either
	 * through regular routes or station coverage.
	 */
	public boolean canConnect( CityPair cityPair ) {

		if ( cities.contains( cityPair.getCity1() ) || this.citiesCovered.contains( cityPair.getCity1() ) )
			if ( cities.contains( cityPair.getCity2() ) || this.citiesCovered.contains( cityPair.getCity2() ) )
				return true;

		return false;
	}

	/**
	 * Returns true if and only if this network connects both cities, either through regular routes or station coverage.
	 */
	public boolean canConnect( CityPairInfo cityPair ) {
		boolean rBool = false;
		
		CityInfo city1info = cityPair.getCity1();
		CityInfo city2info = cityPair.getCity2();
		Set<CityInfo> connected = InfoWrapper.wrapToSet( this.cities);
		connected.addAll( InfoWrapper.wrapToSet( this.citiesCovered ) );
		
		return ( connected.contains( city1info ) &&  connected.contains( city2info ));
	}
	
	/**
	 * Helper method that returns all the Routes of unused that have head as one of their cities.
	 */
	private Set<Route> connectors( City head, Set<Route> unused ){
		
		Set<Route> connected = new HashSet<>();
		
		for ( Route checkMe : unused )
			if ( checkMe.getCities().contains( head ) )
				connected.add( checkMe );
				
		return connected;
		
	}

}
