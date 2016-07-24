package com.underplex.tickay.info;

import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.game.City;

/**
 * Immutable wrapper for a city in the game.
 * @author Brandon Irvine
 *
 */
public class CityInfo {
  
	private City source;
  
	/**
	 * Constructor for simulator.
	 * @param source	the <code>City</code> this represents
	 */
	public CityInfo(City source){
		this.source = source;
	}

	public String toString() {
		return source.toString();
	}
	
	/**
	 * Returns <code>true</code> if and only if this city has a station built on it.
	 * @return <code>boolean</code> showing whether this city has a station
	 */
	public boolean hasStation() {
		return source.hasStation();
	}

	/**
	 * Returns name of city.
	 * <p>
	 * Names are as given on the English version of the board (as printed in the U.S.), though not guaranteed to be in correct case.
	 */
	public String getName() {
		return source.getName();
	}

	/**
	 * Returns <code>Set</code> representing all routes with one end in this city.
	 */
	public Set<RouteInfo> getRoutes() {
		return InfoWrapper.wrapToSet( source.getRoutes() );
	}

	/**
	 * Returns <code>Set</code> representing all routes with one end in this city that are also built.
	 */
	public Set<RouteInfo> builtRoutes() {
		return InfoWrapper.wrapToSet( source.builtRoutes() );
	}

	/**
	 * Returns player that built a station at this city or <code>null</code> if none. 
	 */
	public PlayerInfo getBuilder(){
		return source.getBuilder().info();
	}

	/**
	 * Returns column letter roughly approximating West-East location.
	 * <p>
	 * Column A is the far left of the map, with letters advancing further East.
	 * <p>
	 * Cities in a game are given unique col-row combinations when the board is created.
	 */
	public char getCol() {
		return source.getCol();
	}

	/**
	 * Returns row number roughly approximating North-South location.
	 * <p>
	 * Row 1 is the far left of the map, with numbers advancing further South.
	 * <p>
	 * Cities in a game are given unique col-row combinations when the board is created.
	 */
	public int getRow() {
		return source.getRow();
	}
	
	public boolean equals(Object obj) {
		return source.equals(obj);
	}
	
	/**
	 * Returns <code>true</code> if this <code>city1</code> should appear before <code>city2</code> on ticket.
	 */
	public static boolean isOrdered( CityInfo city1, CityInfo city2 ) {
		boolean rBool = false;
		// find the absolute difference in columns
		int colDiff = Math.abs( Character.getNumericValue( city1.getCol() ) - Character.getNumericValue( city2.getCol() ) );
		// find the absolute difference in columns
		int rowDiff = Math.abs( city1.getRow() - city2.getRow() );
		// now compare on the more prevalent difference
		if ( colDiff > rowDiff ){
			if ( city1.getCol() < city2.getCol() ){
				rBool = true;
			} 		
		} else { // default priority is given to rows
			if ( city1.getRow() < city2.getRow() )
				rBool = true;
		}
		
		return rBool;
	}
	

	
}