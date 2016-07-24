package com.underplex.tickay.game;

import java.util.HashSet;
import java.util.Set;

import com.underplex.tickay.player.Player;

/**
 * Represents a series of routes connecting two cities, with no forking or circular connection.
 * <p>
 * The path may not be the shortest set of routes between two cities.
 * <p>
 * A Path cannot use or include a station in any way.
 * <p>
 * The main purpose of a Path is as a candidate for longest continuous path at the end of the game.
 */
public class Path implements Comparable<Path> {

	private Player player;
	private Set<Route> routes; // routes included in the path
	private City tail;
	private City head;

	private Path(){
		// needed for the copy method
	}
	
	public Path( City city, Player player ){
		this.routes = new HashSet<>();
		this.player = player;
		this.tail = city;
		this.head = city;
	}

	public String toString(){
		return "Path from " + this.getEnds().toString() + " of length " + this.length() + " with routes: " + this.routes.toString();
	}
	
	public CityPair getEnds() {
		return new CityPair(this.tail, this.head);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer(){
		return this.player;
	}
	
	public Set<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
	}
	public City getTail() {
		return tail;
	}

	public void setTail(City tail) {
		this.tail = tail;
	}

	public City getHead() {
		return head;
	}

	public void setHead(City head) {
		this.head = head;
	}

	public int length() {
		int rInt = 0;
		for (Route r : this.routes)
			rInt += r.getLength();
		return rInt;
	}

	/**
	 * Returns Set of Strings representing Routes.
	 */
	public Set<String> getRouteStrings(){
		Set<String> rSet = new HashSet<>();
		for ( Route r : routes )
			rSet.add( r.toString() );
		
		return rSet;
	}
	
	public int compareTo(Path other) {
		int rValue = 0;
		if (this.length() > other.length()) {
			rValue = 1;
		} else if (this.length() < other.length()) {
			rValue = -1;
		}
		return rValue;
	}

	/**
	 * Return true if specified route can be added to this path.
	 * <p>
	 * If the route is already part of this path, true will still be returned.
	 */
	public boolean canAddRoute( Route route ) {
		return ( route.getCities().contains(this.tail) || route.getCities().contains(this.head) );
	}

	/**
	 * Adds the route to this path if it can be.
	 * <p>
	 * This makes no checks on whether it is already part of the path or whether it's legal to add it.
	 */
	public void addRoute( Route route ){
		
		this.routes.add(route);
   }// end method

	public Path copy() {
		Path rPath = new Path();
		rPath.setPlayer( this.player );
		rPath.setRoutes( new HashSet<Route>(this.routes) );
		rPath.setHead( this.head );
		rPath.setTail( this.tail );

		return rPath;
	}

	/**
	 * Returns true if Paths share all of the same routes and belong to the same europlayer.
	 * <p>
	 * While the ends are useful to understanding a path, they don't really characterize or identify them.
	 */
	public boolean equals( Object o ) {
		boolean rBool = true;
		
		if ( o == null ){
			rBool = false;
		} else if ( o instanceof Path ) {
			Path path = (Path)o;
			
			if ( !this.player.equals( path.getPlayer() ) )
				rBool = false;
			
			if ( !this.routes.equals( path.getRoutes() ) )
				rBool = false;

		} else {
			rBool = false;
		}
		return rBool;
	}

	public int hashCode(){
		return routes.hashCode() * this.player.hashCode();
	}
	
	
	// getters, setters
}
