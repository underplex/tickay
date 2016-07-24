package com.underplex.tickay.game;

import java.util.HashSet;
import java.util.Set;

import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tool.Finder;

/**
 * Utility class to produce components of the board in the board game Ticket to Ride.
 * @author Brandon Irvine
 */
public class BoardFactory {

	private BoardFactory(){
		// don't instantiate
	}

	public static Set<Route> makeRoutes( String expansion, Set<City> cities ) {

		Set<Route> rSet = new HashSet<>();
		
		if ( expansion.toUpperCase().contains( "EUROPE") ){
			rSet = makeEuroRoutes(cities);
		} else if ( expansion.toUpperCase().contains( "AMERICA") ) {
			rSet = makeAmericaRoutes(cities);
		}
		
		return rSet;
	}
	
	// all routes
	// https://boardgamegeek.com/thread/62863/listing-destination-tickets-and-enumeration-cities
	
	/**
	 * Returns all routes for the Europe game.
	 * <p>
	 * Parameter <code>cities</code> will need to be the pointers to the actual cities.
	 */
	public static Set<Route> makeEuroRoutes( Set<City> cities ){
		
		Set<Route> rSet = new HashSet<>();
		Route route; // use this to carry a newly created route to its alternate
		Route routeAlt; // use this to tell the route what its alternate must be

		// all non-alternate, non-tunnel, non-loco routes
		rSet.add(new Route(2, Finder.find(cities, "CADIZ"), Finder.find(cities, "LISBOA"), TrainType.BLUE));

		rSet.add(new Route(2, Finder.find(cities, "BRINDISI"), 
				Finder.find(cities, "ROMA"), TrainType.WHITE));

		rSet.add(new Route(3, Finder.find(cities, "MADRID"), 
				Finder.find(cities, "LISBOA"), TrainType.PURPLE));
		
		rSet.add(new Route(3, Finder.find(cities, "MADRID"), 
				Finder.find(cities, "CADIZ"), TrainType.ORANGE));

		rSet.add(new Route(2, Finder.find(cities, "MADRID"), 
				Finder.find(cities, "BARCELONA"), TrainType.YELLOW));

		rSet.add(new Route(4, Finder.find(cities, "MARSEILLE"), 
				Finder.find(cities, "PAMPLONA"), TrainType.RED));

		rSet.add(new Route(4, Finder.find(cities, "MARSEILLE"), 
				Finder.find(cities, "PARIS"), TrainType.GRAY));

		rSet.add(new Route(4, Finder.find(cities, "MARSEILLE"), 
				Finder.find(cities, "BARCELONA"), TrainType.GRAY));

		rSet.add(new Route(4, Finder.find(cities, "BREST"), 
				Finder.find(cities, "PAMPLONA"), TrainType.PURPLE));

		rSet.add(new Route(3, Finder.find(cities, "BREST"), 
				Finder.find(cities, "PARIS"), TrainType.BLACK));

		rSet.add(new Route(2, Finder.find(cities, "BREST"), 
				Finder.find(cities, "DIEPPE"), TrainType.ORANGE));

		rSet.add(new Route(1, Finder.find(cities, "DIEPPE"), 
				Finder.find(cities, "PARIS"), TrainType.PURPLE));

		rSet.add(new Route(2, Finder.find(cities, "DIEPPE"), 
				Finder.find(cities, "BRUXELLES"), TrainType.GREEN));

		rSet.add(new Route(1, Finder.find(cities, "AMSTERDAM"), 
				Finder.find(cities, "BRUXELLES"), TrainType.BLACK));

		rSet.add(new Route(2, Finder.find(cities, "FRANKFURT"), 
				Finder.find(cities, "BRUXELLES"), TrainType.BLUE));

		rSet.add(new Route(2, Finder.find(cities, "FRANKFURT"), 
				Finder.find(cities, "AMSTERDAM"), TrainType.WHITE));

		rSet.add(new Route(3, Finder.find(cities, "AMSTERDAM"), 
				Finder.find(cities, "ESSEN"), TrainType.YELLOW));

		rSet.add(new Route(2, Finder.find(cities, "FRANKFURT"), 
				Finder.find(cities, "ESSEN"), TrainType.GREEN));

		rSet.add(new Route(2, Finder.find(cities, "BERLIN"), 
				Finder.find(cities, "ESSEN"), TrainType.BLUE));

		rSet.add(new Route(4, Finder.find(cities, "BERLIN"), 
				Finder.find(cities, "DANZIG"), TrainType.GRAY));
		
		rSet.add(new Route(2, Finder.find(cities, "DANZIG"), 
				Finder.find(cities, "WARSZAWA"), TrainType.GRAY));
		
		rSet.add(new Route(3, Finder.find(cities, "WARSZAWA"), 
				Finder.find(cities, "WILNO"), TrainType.RED));
		
		rSet.add(new Route(3, Finder.find(cities, "SMOLENSK"), 
				Finder.find(cities, "WILNO"), TrainType.YELLOW));

		rSet.add(new Route(4, Finder.find(cities, "PETROGRAD"), 
				Finder.find(cities, "WILNO"), TrainType.BLUE));
		
		rSet.add(new Route(4, Finder.find(cities, "WILNO"), 
				Finder.find(cities, "RIGA"), TrainType.GREEN));

		rSet.add(new Route(2, Finder.find(cities, "VENEZIA"), 
				Finder.find(cities, "ZAGREB"), TrainType.GRAY));

		rSet.add(new Route(2, Finder.find(cities, "WIEN"), 
				Finder.find(cities, "ZAGREB"), TrainType.GRAY));
		
		rSet.add(new Route(3, Finder.find(cities, "DANZIG"), 
				Finder.find(cities, "RIGA"), TrainType.BLACK));
		rSet.add(new Route(4, Finder.find(cities, "RIGA"), 
				Finder.find(cities, "PETROGRAD"), TrainType.GRAY));
		rSet.add(new Route(2, Finder.find(cities, "WILNO"), 
				Finder.find(cities, "KYIV"), TrainType.GRAY));
		rSet.add(new Route(3, Finder.find(cities, "KYIV"), 
				Finder.find(cities, "SMOLENSK"), TrainType.RED));
		rSet.add(new Route(2, Finder.find(cities, "SMOLENSK"), 
				Finder.find(cities, "MOSKVA"), TrainType.ORANGE));
		rSet.add(new Route(4, Finder.find(cities, "MOSKVA"), 
				Finder.find(cities, "PETROGRAD"), TrainType.WHITE));
		rSet.add(new Route(4, Finder.find(cities, "MOSKVA"), 
				Finder.find(cities, "KHARKOV"), TrainType.GRAY));
		rSet.add(new Route(4, Finder.find(cities, "KYIV"), 
				Finder.find(cities, "WARSZAWA"), TrainType.GRAY));
		rSet.add(new Route(4, Finder.find(cities, "KYIV"), 
				Finder.find(cities, "KHARKOV"), TrainType.GRAY));
		rSet.add(new Route(4, Finder.find(cities, "KYIV"), 
				Finder.find(cities, "BUCURESTI"), TrainType.GRAY));
		rSet.add(new Route(2, Finder.find(cities, "KHARKOV"), 
				Finder.find(cities, "ROSTOV"), TrainType.GREEN));
		rSet.add(new Route(4, Finder.find(cities, "SEVASTOPOL"), 
				Finder.find(cities, "ROSTOV"), TrainType.GRAY));
		rSet.add(new Route(2, Finder.find(cities, "SOCHI"),
				Finder.find(cities, "ROSTOV"), TrainType.GRAY));
		rSet.add(new Route(4, Finder.find(cities, "SEVASTOPOL"), 
				Finder.find(cities, "BUCURESTI"), TrainType.WHITE));
		rSet.add(new Route(3, Finder.find(cities, "CONSTANTINOPLE"),
				Finder.find(cities, "BUCURESTI"), TrainType.YELLOW));
		rSet.add(new Route(3, Finder.find(cities, "ERZURUM"), 
				Finder.find(cities, "ANGORA"), TrainType.BLACK));
		rSet.add(new Route(3, Finder.find(cities, "CONSTANTINOPLE"),
				Finder.find(cities, "SOFIA"), TrainType.BLUE));
		rSet.add(new Route(3, Finder.find(cities, "ATHINA"), 
				Finder.find(cities, "SOFIA"), TrainType.PURPLE));
		rSet.add(new Route(4, Finder.find(cities, "ATHINA"), 
				Finder.find(cities, "SARAJEVO"), TrainType.GREEN));
		rSet.add(new Route(3, Finder.find(cities, "ZAGREB"), 
				Finder.find(cities, "SARAJEVO"), TrainType.RED));
		rSet.add(new Route(3, Finder.find(cities, "BUDAPEST"), 
				Finder.find(cities, "SARAJEVO"), TrainType.PURPLE));
		rSet.add(new Route(2, Finder.find(cities, "BUDAPEST"), 
				Finder.find(cities, "ZAGREB"), TrainType.ORANGE));
		rSet.add(new Route(3, Finder.find(cities, "MUNCHEN"), 
				Finder.find(cities, "WIEN"), TrainType.ORANGE));
		rSet.add(new Route(4, Finder.find(cities, "WARSZAWA"), 
				Finder.find(cities, "WIEN"), TrainType.BLUE));
		rSet.add(new Route(2, Finder.find(cities, "MUNCHEN"), 
				Finder.find(cities, "FRANKFURT"), TrainType.PURPLE));
		rSet.add(new Route(3, Finder.find(cities, "BERLIN"), 
				Finder.find(cities, "WIEN"), TrainType.GREEN));

		// add all routes that have alternates
		route = new Route(3, Finder.find(cities, "MADRID"), Finder.find(cities, "PAMPLONA"), TrainType.BLACK, true, 0);
		routeAlt = new Route(route, TrainType.WHITE);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(1, Finder.find(cities, "WIEN"), Finder.find(cities, "BUDAPEST"), TrainType.WHITE, false, 0);
		routeAlt = new Route(route, TrainType.RED);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(2, Finder.find(cities, "PARIS"), Finder.find(cities, "BRUXELLES"), TrainType.RED, true, 0);
		routeAlt = new Route(route, TrainType.YELLOW);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);
		
		route = new Route(2, Finder.find(cities, "DIEPPE"),Finder.find(cities, "LONDON"), TrainType.GRAY, false, 1);
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(4, Finder.find(cities, "EDINBURGH"),Finder.find(cities, "LONDON"), TrainType.BLACK, false, 0);
		routeAlt = new Route(route, TrainType.ORANGE);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(3, Finder.find(cities, "ESSEN"),Finder.find(cities, "KOBENHAVN"), TrainType.GRAY, false, 1);
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(3, Finder.find(cities, "KOBENHAVN"), Finder.find(cities, "STOCKHOLM"), TrainType.WHITE,
				false, 0);
		routeAlt = new Route(route, TrainType.YELLOW);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(4, Finder.find(cities, "PARIS"), Finder.find(cities, "PAMPLONA"), TrainType.BLUE, false, 0);
		routeAlt = new Route(route, TrainType.GREEN);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(3, Finder.find(cities, "PARIS"), Finder.find(cities, "FRANKFURT"), TrainType.WHITE, false, 0);
		routeAlt = new Route(route, TrainType.ORANGE);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(3, Finder.find(cities, "FRANKFURT"), Finder.find(cities, "BERLIN"), TrainType.BLACK, false, 0);
		routeAlt = new Route(route, TrainType.RED);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(4, Finder.find(cities, "BERLIN"), Finder.find(cities, "WARSZAWA"), TrainType.PURPLE, false, 0);
		routeAlt = new Route(route, TrainType.YELLOW);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);
		
		// tunnels (except for alternates)
		rSet.add(new Route(2, Finder.find(cities, "MARSEILLE"), 
				Finder.find(cities, "ZURICH"), TrainType.PURPLE, true, 0));
		rSet.add(new Route(2, Finder.find(cities, "PAMPLONA"), 
				Finder.find(cities, "BARCELONA"), TrainType.GRAY, true, 0));

		rSet.add(new Route(4, Finder.find(cities, "MARSEILLE"), 
				Finder.find(cities, "ROMA"), TrainType.GRAY, true, 0));
		rSet.add(new Route(2, Finder.find(cities, "ZURICH"), 
				Finder.find(cities, "VENEZIA"), TrainType.GREEN, true, 0));
		rSet.add(new Route(3, Finder.find(cities, "ZURICH"), 
				Finder.find(cities, "PARIS"), TrainType.GRAY, true, 0));
		rSet.add(new Route(8, Finder.find(cities, "STOCKHOLM"), 
				Finder.find(cities, "PETROGRAD"), TrainType.GRAY, true, 0));
		rSet.add(new Route(6, Finder.find(cities, "BUDAPEST"), 
				Finder.find(cities, "KYIV"), TrainType.GRAY, true, 0));
		rSet.add(new Route(4, Finder.find(cities, "BUDAPEST"), 
				Finder.find(cities, "BUCURESTI"), TrainType.GRAY, true, 0));
		rSet.add(new Route(2, Finder.find(cities, "BUCURESTI"), 
				Finder.find(cities, "SOFIA"), TrainType.GRAY, true, 0));
		rSet.add(new Route(2, Finder.find(cities, "SARAJEVO"), 
				Finder.find(cities, "SOFIA"), TrainType.GRAY, true, 0));
		rSet.add(new Route(2, Finder.find(cities, "VENEZIA"), 
				Finder.find(cities, "MUNCHEN"), TrainType.BLUE, true, 0));
		rSet.add(new Route(2, Finder.find(cities, "ZURICH"), 
				Finder.find(cities, "MUNCHEN"), TrainType.YELLOW, true, 0));
		rSet.add(new Route(2, Finder.find(cities, "SMYRNA"), 
				Finder.find(cities, "CONSTANTINOPLE"), TrainType.GRAY, true, 0));
		rSet.add(new Route(3, Finder.find(cities, "SMYRNA"), 
				Finder.find(cities, "ANGORA"), TrainType.ORANGE, true, 0));
		rSet.add(new Route(3, Finder.find(cities, "SOCHI"), 
				Finder.find(cities, "ERZURUM"), TrainType.RED, true, 0));
		rSet.add(new Route(2, Finder.find(cities, "ANGORA"), 
				Finder.find(cities, "CONSTANTINOPLE"), TrainType.GRAY, true, 0));

		// locos (except for alternates)
		rSet.add(new Route(4, Finder.find(cities, "PALERMO"), 
				Finder.find(cities, "ROMA"), TrainType.GRAY, false, 1));
		rSet.add(new Route(3, Finder.find(cities, "PALERMO"), 
				Finder.find(cities, "BRINDISI"), TrainType.GRAY, false, 1));
		rSet.add(new Route(6, Finder.find(cities, "PALERMO"), 
				Finder.find(cities, "SMYRNA"), TrainType.GRAY, false, 2));
		rSet.add(new Route(2, Finder.find(cities, "ATHINA"), 
				Finder.find(cities, "SMYRNA"), TrainType.GRAY, false, 1));
		rSet.add(new Route(4, Finder.find(cities, "ATHINA"), 
				Finder.find(cities, "BRINDISI"), TrainType.GRAY, false, 1));
		rSet.add(new Route(4, Finder.find(cities, "SEVASTOPOL"), 
				Finder.find(cities, "CONSTANTINOPLE"), TrainType.GRAY, false,
				2));
		rSet.add(new Route(4, Finder.find(cities, "SEVASTOPOL"), 
				Finder.find(cities, "ERZURUM"), TrainType.GRAY, false, 2));
		rSet.add(new Route(2, Finder.find(cities, "SEVASTOPOL"), 
				Finder.find(cities, "SOCHI"), TrainType.GRAY, false, 1));
		rSet.add(new Route(2, Finder.find(cities, "LONDON"), 
				Finder.find(cities, "AMSTERDAM"), TrainType.GRAY, false, 2));

		return rSet;
	}

	/**
	 * Returns all routes for the America game.
	 * <p>
	 * Parameter <code>cities</code> will need to be the pointers to the actual cities.
	 */
	public static Set<Route> makeAmericaRoutes( Set<City> cities ){
		
		Set<Route> rSet = new HashSet<>();
		Route route; // use this to carry a newly created route to its alternate
		Route routeAlt; // use this to tell the route what its alternate must be

		// all non-alternate routes

		rSet.add(new Route(3, Finder.find( cities, "VANCOUVER" ), 
				Finder.find( cities, "CALGARY" ), 
				TrainType.GRAY));

		rSet.add(new Route(4, Finder.find( cities, "SEATTLE" ), 
				Finder.find( cities, "CALGARY" ), 
				TrainType.GRAY));

		rSet.add(new Route(4, Finder.find( cities, "HELENA" ), 
				Finder.find( cities, "CALGARY" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(6, Finder.find( cities, "WINNIPEG" ), 
				Finder.find( cities, "CALGARY" ),
				TrainType.WHITE));
				
		rSet.add(new Route(4, Finder.find( cities, "HELENA" ), 
				Finder.find( cities, "WINNIPEG" ), 
				TrainType.BLUE));
		
		rSet.add(new Route(4, Finder.find( cities, "HELENA" ), 
				Finder.find( cities, "DENVER" ), 
				TrainType.GREEN));
		
		rSet.add(new Route(3, Finder.find( cities, "HELENA" ), 
				Finder.find( cities, "SALT LAKE CITY" ), 
				TrainType.PURPLE));

		rSet.add(new Route(5, Finder.find( cities, "HELENA" ), 
				Finder.find( cities, "OMAHA" ), 
				TrainType.RED));

		rSet.add(new Route(6, Finder.find( cities, "HELENA" ), 
				Finder.find( cities, "DULUTH" ), 
				TrainType.ORANGE));

		rSet.add(new Route(6, Finder.find( cities, "PORTLAND" ), 
				Finder.find( cities, "SALT LAKE CITY" ), 
				TrainType.BLUE));

		rSet.add(new Route(3, Finder.find( cities, "LAS VEGAS" ), 
				Finder.find( cities, "SALT LAKE CITY" ), 
				TrainType.ORANGE));

		rSet.add(new Route(2, Finder.find( cities, "LAS VEGAS" ), 
				Finder.find( cities, "LOS ANGELES" ), 
				TrainType.GRAY));

		rSet.add(new Route(3, Finder.find( cities, "PHOENIX" ), 
				Finder.find( cities, "LOS ANGELES" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(6, Finder.find( cities, "EL PASO" ), 
				Finder.find( cities, "LOS ANGELES" ), 
				TrainType.BLACK));

		rSet.add(new Route(3, Finder.find( cities, "PHOENIX" ), 
				Finder.find( cities, "EL PASO" ), 
				TrainType.GRAY));

		rSet.add(new Route(3, Finder.find( cities, "PHOENIX" ), 
				Finder.find( cities, "SANTA FE" ), 
				TrainType.GRAY));

		rSet.add(new Route(5, Finder.find( cities, "PHOENIX" ), 
				Finder.find( cities, "DENVER" ), 
				TrainType.WHITE));

		rSet.add(new Route(2, Finder.find( cities, "SANTA FE" ), 
				Finder.find( cities, "DENVER" ), 
				TrainType.GRAY));

		rSet.add(new Route(3, Finder.find( cities, "SANTA FE" ), 
				Finder.find( cities, "OKLAHOMA CITY" ), 
				TrainType.BLUE));
		
		rSet.add(new Route(2, Finder.find( cities, "SANTA FE" ), 
				Finder.find( cities, "EL PASO" ), 
				TrainType.GRAY));

		rSet.add(new Route(4, Finder.find( cities, "OKLAHOMA CITY" ), 
				Finder.find( cities, "DENVER" ), 
				TrainType.RED));
		
		rSet.add(new Route(4, Finder.find( cities, "OMAHA" ), 
				Finder.find( cities, "DENVER" ), 
				TrainType.PURPLE));

		rSet.add(new Route(5, Finder.find( cities, "OKLAHOMA CITY" ), 
				Finder.find( cities, "EL PASO" ), 
				TrainType.YELLOW));

		rSet.add(new Route(4, Finder.find( cities, "DALLAS" ), 
				Finder.find( cities, "EL PASO" ), 
				TrainType.RED));
		
		rSet.add(new Route(6, Finder.find( cities, "HOUSTON" ), 
				Finder.find( cities, "EL PASO" ), 
				TrainType.GREEN));
		
		rSet.add(new Route(2, Finder.find( cities, "HOUSTON" ), 
				Finder.find( cities, "NEW ORLEANS" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(3, Finder.find( cities, "LITTLE ROCK" ), 
				Finder.find( cities, "NEW ORLEANS" ), 
				TrainType.GREEN));
		
		rSet.add(new Route(6, Finder.find( cities, "MIAMI" ), 
				Finder.find( cities, "NEW ORLEANS" ), 
				TrainType.RED));
		
		rSet.add(new Route(2, Finder.find( cities, "LITTLE ROCK" ), 
				Finder.find( cities, "OKLAHOMA CITY" ), 
				TrainType.GRAY));

		rSet.add(new Route(2, Finder.find( cities, "LITTLE ROCK" ), 
				Finder.find( cities, "SAINT LOUIS" ), 
				TrainType.GRAY));

		rSet.add(new Route(3, Finder.find( cities, "LITTLE ROCK" ), 
				Finder.find( cities, "NASHVILLE" ), 
				TrainType.WHITE));
		
		rSet.add(new Route(3, Finder.find( cities, "DULUTH" ), 
				Finder.find( cities, "SAULT ST. MARIE" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(6, Finder.find( cities, "DULUTH" ), 
				Finder.find( cities, "TORONTO" ), 
				TrainType.PURPLE));
		
		rSet.add(new Route(3, Finder.find( cities, "DULUTH" ), 
				Finder.find( cities, "CHICAGO" ), 
				TrainType.RED));
		
		rSet.add(new Route(4, Finder.find( cities, "OMAHA" ), 
				Finder.find( cities, "CHICAGO" ), 
				TrainType.BLUE));

		rSet.add(new Route(5, Finder.find( cities, "MONTREAL" ), 
				Finder.find( cities, "SAULT ST. MARIE" ), 
				TrainType.BLACK));
		
		rSet.add(new Route(3, Finder.find( cities, "MONTREAL" ), 
				Finder.find( cities, "TORONTO" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(2, Finder.find( cities, "SAULT ST. MARIE" ), 
				Finder.find( cities, "TORONTO" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(2, Finder.find( cities, "PITTSBURGH" ), 
				Finder.find( cities, "TORONTO" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(4, Finder.find( cities, "CHICAGO" ), 
				Finder.find( cities, "TORONTO" ), 
				TrainType.WHITE));
		
		rSet.add(new Route(2, Finder.find( cities, "PITTSBURGH" ), 
				Finder.find( cities, "WASHINGTON" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(2, Finder.find( cities, "PITTSBURGH" ), 
				Finder.find( cities, "RALEIGH" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(4, Finder.find( cities, "PITTSBURGH" ), 
				Finder.find( cities, "NASHVILLE" ), 
				TrainType.YELLOW));
		
		rSet.add(new Route(5, Finder.find( cities, "PITTSBURGH" ), 
				Finder.find( cities, "SAINT LOUIS" ), 
				TrainType.GREEN));
		
		rSet.add(new Route(2, Finder.find( cities, "SAINT LOUIS" ), 
				Finder.find( cities, "NASHVILLE" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(1, Finder.find( cities, "ATLANTA" ), 
				Finder.find( cities, "NASHVILLE" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(3, Finder.find( cities, "RALEIGH" ), 
				Finder.find( cities, "NASHVILLE" ), 
				TrainType.BLACK));

		rSet.add(new Route(2, Finder.find( cities, "LITTLE ROCK" ), 
				Finder.find( cities, "NASHVILLE" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(5, Finder.find( cities, "ATLANTA" ), 
				Finder.find( cities, "MIAMI" ), 
				TrainType.BLUE));

		rSet.add(new Route(2, Finder.find( cities, "ATLANTA" ), 
				Finder.find( cities, "CHARLESTON" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(4, Finder.find( cities, "MIAMI" ), 
				Finder.find( cities, "CHARLESTON" ), 
				TrainType.PURPLE));
		
		rSet.add(new Route(2, Finder.find( cities, "CHARLESTON" ), 
				Finder.find( cities, "RALEIGH" ), 
				TrainType.GRAY));
		
		rSet.add(new Route(3, Finder.find( cities, "MONTREAL" ), 
				Finder.find( cities, "NEW YORK" ), 
				TrainType.BLUE));
		
		// alternate routes
		
		route = new Route(1, Finder.find(cities, "VANCOUVER"), 
				Finder.find(cities, "SEATTLE"), 
				TrainType.GRAY);
		
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(1, Finder.find(cities, "PORTLAND"), 
				Finder.find(cities, "SEATTLE"), 
				TrainType.GRAY);
		
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(5, Finder.find(cities, "PORTLAND"), 
				Finder.find(cities, "SAN FRANCISCO"), 
				TrainType.GREEN);
		
		routeAlt = new Route(route, TrainType.PURPLE);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);
		
		route = new Route(3, Finder.find(cities, "LOS ANGELES"), 
				Finder.find(cities, "SAN FRANCISCO"), 
				TrainType.YELLOW);
		
		routeAlt = new Route(route, TrainType.PURPLE);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(5, Finder.find(cities, "SALT LAKE CITY"), 
				Finder.find(cities, "SAN FRANCISCO"), 
				TrainType.WHITE);
		
		routeAlt = new Route(route, TrainType.ORANGE);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);


		route = new Route(3, Finder.find(cities, "SALT LAKE CITY"), 
				Finder.find(cities, "DENVER"), 
				TrainType.RED);
		
		routeAlt = new Route(route, TrainType.YELLOW);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);


		route = new Route(4, Finder.find(cities, "KANSAS CITY"), 
				Finder.find(cities, "DENVER"), 
				TrainType.BLACK);
		
		routeAlt = new Route(route, TrainType.ORANGE);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(2, Finder.find(cities, "KANSAS CITY"), 
				Finder.find(cities, "SAINT LOUIS"), 
				TrainType.BLUE);
		
		routeAlt = new Route(route, TrainType.PURPLE);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(2, Finder.find(cities, "CHICAGO"), 
				Finder.find(cities, "SAINT LOUIS"), 
				TrainType.WHITE);
		
		routeAlt = new Route(route, TrainType.GREEN);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(2, Finder.find(cities, "CHICAGO"), 
				Finder.find(cities, "PITTSBURGH"), 
				TrainType.BLACK);
		
		routeAlt = new Route(route, TrainType.ORANGE);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(2, Finder.find(cities, "PITTSBURGH"), 
				Finder.find(cities, "NEW YORK"), 
				TrainType.WHITE);
		
		routeAlt = new Route(route, TrainType.GREEN);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(1, Finder.find(cities, "HOUSTON"), 
				Finder.find(cities, "DALLAS"), 
				TrainType.GRAY);
		
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(2, Finder.find(cities, "OKLAHOMA CITY"), 
				Finder.find(cities, "DALLAS"), 
				TrainType.GRAY);
		
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(2, Finder.find(cities, "OKLAHOMA CITY"), 
				Finder.find(cities, "KANSAS CITY"), 
				TrainType.GRAY);
		
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(1, Finder.find(cities, "KANSAS CITY"), 
				Finder.find(cities, "OMAHA"), 
				TrainType.GRAY);
		
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(2, Finder.find(cities, "DULUTH"), 
				Finder.find(cities, "OMAHA"), 
				TrainType.GRAY);
		
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(4, Finder.find(cities, "NEW ORLEANS"), 
				Finder.find(cities, "ATLANTA"), 
				TrainType.ORANGE);
		
		routeAlt = new Route(route, TrainType.YELLOW);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);
		
		route = new Route(2, Finder.find(cities, "RALEIGH"), 
				Finder.find(cities, "ATLANTA"), 
				TrainType.GRAY);
		
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);
		
		route = new Route(2, Finder.find(cities, "RALEIGH"), 
				Finder.find(cities, "WASHINGTON"), 
				TrainType.GRAY);
		
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(2, Finder.find(cities, "NEW YORK"), 
				Finder.find(cities, "WASHINGTON"), 
				TrainType.ORANGE);
		
		routeAlt = new Route(route, TrainType.BLACK);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);

		route = new Route(2, Finder.find(cities, "NEW YORK"), 
				Finder.find(cities, "BOSTON"), 
				TrainType.RED);
		
		routeAlt = new Route(route, TrainType.YELLOW);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);
		
		route = new Route(2, Finder.find(cities, "MONTREAL"), 
				Finder.find(cities, "BOSTON"), 
				TrainType.GRAY);
		
		routeAlt = new Route(route, TrainType.GRAY);
		route.setAlternate(routeAlt);
		rSet.add(route);
		rSet.add(routeAlt);
		return rSet;
	}	
}
