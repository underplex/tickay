package com.underplex.tickay.game;

import java.util.HashSet;
import java.util.Set;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.BoardManagerInfo;
import com.underplex.tickay.info.PlayerInfo;
import com.underplex.tickay.info.RouteInfo;
import com.underplex.tickay.player.Player;

/**
 * Represents the board (cities and routes) for a game of Ticket to Ride.
 * @author Brandon Irvine
 *
 */
public class BoardManager implements InfoSource<BoardManagerInfo> {

	private final Game game;
	private final Set<Route> routes;
	private final Set<City> cities;
	private final BoardManagerInfo info;

	/**
	 * Primary constructor for this.
	 * @param expansions	String with game used (America, Europe, etc.) and all expansions used
	 * @param game			<code>
	 */
	public BoardManager( String expansions, Game game ) {
		this.game = game;
		this.cities = CityFactory.makeCities(expansions);
		this.routes = BoardFactory.makeRoutes( expansions, this.cities );

		this.info = new BoardManagerInfo(this);
	}

	public BoardManagerInfo info(){
		return this.info;
	}
	
	public Set<City> getCities(){
		return this.cities;
	}
	
	public Set<Route> getRoutes(){
		return this.routes;
	}
	
	/**
	 * Returns Set of open cities.
	 */
	public Set<City> openCities() {
		Set<City> rSet = new HashSet<>();
		for (City city : this.cities)
			if (!city.hasStation())
				rSet.add(city);
		return rSet;
	} // end method
		// getters, setters

	/**
	 * Returns set of Routes that player can legally build.
	 * <p>
	 * This doesn't filter for whether the player can afford to built the Route, only that he is legally able to do so.
	 */
	public Set<Route> buildableRoutes( Player player ) {
		Set<Route> rSet = new HashSet<>();
		for ( Route r : routes )
			if ( r.isBuildable( player, this.game ) )
				rSet.add(r);
		return rSet;
	} // end method

	public Set<RouteInfo> buildableRoutes( PlayerInfo playerInfo ) {
		Set<RouteInfo> rSet = new HashSet<>();
		for ( Route r : routes )
			if ( r.isBuildable( playerInfo, this.game ) )
				rSet.add( r.info() );
		return rSet;	} // end method

}
