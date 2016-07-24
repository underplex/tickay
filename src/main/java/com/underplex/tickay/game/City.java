package com.underplex.tickay.game;

import java.util.HashSet;
import java.util.Set;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.CityInfo;
import com.underplex.tickay.player.EuroPlayer;

/**
 * Represents a city in a version of Ticket to Ride board game.
 * <p>
 * In the actual game, there are no rows or columns given for cities, but for various reasons, it is easier to force cities into a rough grid
 * with columns given by letters and rows given by numbers, starting with A on far left and 1 at the top.
 * <p>
 * In this scheme, every city is given a unique combination of letter-number but every combination of letter-number is not necessarily a city.
 * <p>
 * The letter-number combos are used only to compare cities, not as any sort of large, objective measure of how far "south" or "east" a city is. 
 * @author Brandon Irvine
 *
 */
public class City implements InfoSource<CityInfo> {

	private final String name;
	private final char col;
	private final int row;
	private final Set<Route> routes;
	private final CityInfo cityInfo;

	private EuroPlayer station;
	
	public City( String name, char col, int row ) {
		this.col = Character.toUpperCase(col);
		this.row = row;
		this.name = name;
		this.cityInfo = new CityInfo( this );
		this.routes = new HashSet<>();
	}

	public CityInfo info(){
		return cityInfo;
	}
	
	public String toString(){
		return this.name.toUpperCase();
	}
	
	public boolean hasStation() {
		return ( station != null );
	}

	public void placeStation(EuroPlayer euroPlayer) {
		this.station = euroPlayer;
	}

	public EuroPlayer getBuilder(){
		return this.station;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Set<Route> getRoutes() {
		return routes;
	}
	
	public char getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public Set<Route> builtRoutes() {
		Set<Route> rSet = new HashSet<>();
		for (Route route : this.routes)
			if (route.isBuilt())
				rSet.add(route);

		return rSet;
	}
	
}