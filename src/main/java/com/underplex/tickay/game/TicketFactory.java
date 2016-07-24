package com.underplex.tickay.game;

import java.util.HashSet;
import java.util.Set;

import com.underplex.tool.Finder;

public class TicketFactory {

	// https://boardgamegeek.com/thread/62863/listing-destination-tickets-and-enumeration-cities
	/**
	 * Returns Set representing all short tickets in the game.
	 * 
	 * The parameter cities must contain references to the actual cities being
	 * used in the game.
	 */

	public static Set<Ticket> makeEuropeShorts(Set<City> cities) {
		Set<Ticket> rSet = new HashSet<>();
		rSet.add(new Ticket(Finder.find(cities, "ATHINA"), Finder.find(
				cities, "ANGORA"), 5));
		rSet.add(new Ticket(Finder.find(cities, "BUDAPEST"), Finder
				.find(cities, "SOFIA"), 5));
		rSet.add(new Ticket(Finder.find(cities, "FRANKFURT"), Finder
				.find(cities, "KOBENHAVN"), 5));
		rSet.add(new Ticket(Finder.find(cities, "ROSTOV"), Finder.find(
				cities, "ERZURUM"), 5));
		rSet.add(new Ticket(Finder.find(cities, "SOFIA"), Finder.find(
				cities, "SMYRNA"), 5));
		rSet.add(new Ticket(Finder.find(cities, "KYIV"), Finder.find(
				cities, "PETROGRAD"), 6));
		rSet.add(new Ticket(Finder.find(cities, "ZURICH"), Finder.find(
				cities, "BRINDISI"), 6));
		rSet.add(new Ticket(Finder.find(cities, "ZURICH"), Finder.find(
				cities, "BUDAPEST"), 6));
		rSet.add(new Ticket(Finder.find(cities, "WARSZAWA"), Finder
				.find(cities, "SMOLENSK"), 6));
		rSet.add(new Ticket(Finder.find(cities, "ZAGREB"), Finder.find(
				cities, "BRINDISI"), 6));
		rSet.add(new Ticket(Finder.find(cities, "PARIS"), Finder.find(
				cities, "ZAGREB"), 7));
		rSet.add(new Ticket(Finder.find(cities, "BREST"), Finder.find(
				cities, "MARSEILLE"), 7));
		rSet.add(new Ticket(Finder.find(cities, "LONDON"), Finder.find(
				cities, "BERLIN"), 7));
		rSet.add(new Ticket(Finder.find(cities, "EDINBURGH"), Finder
				.find(cities, "PARIS"), 7));
		rSet.add(new Ticket(Finder.find(cities, "AMSTERDAM"), Finder
				.find(cities, "PAMPLONA"), 7));
		rSet.add(new Ticket(Finder.find(cities, "ROMA"), Finder.find(
				cities, "SMYRNA"), 8));
		rSet.add(new Ticket(Finder.find(cities, "PALERMO"), Finder
				.find(cities, "CONSTANTINOPLE"), 8));
		rSet.add(new Ticket(Finder.find(cities, "SARAJEVO"), Finder
				.find(cities, "SEVASTOPOL"), 8));
		rSet.add(new Ticket(Finder.find(cities, "MADRID"), Finder.find(
				cities, "DIEPPE"), 8));
		rSet.add(new Ticket(Finder.find(cities, "BARCELONA"), Finder
				.find(cities, "BRUXELLES"), 8));
		rSet.add(new Ticket(Finder.find(cities, "PARIS"), Finder.find(
				cities, "WIEN"), 8));
		rSet.add(new Ticket(Finder.find(cities, "BARCELONA"), Finder
				.find(cities, "MUNCHEN"), 8));
		rSet.add(new Ticket(Finder.find(cities, "BREST"), Finder.find(
				cities, "VENEZIA"), 8));
		rSet.add(new Ticket(Finder.find(cities, "SMOLENSK"), Finder
				.find(cities, "ROSTOV"), 8));
		rSet.add(new Ticket(Finder.find(cities, "MARSEILLE"), Finder
				.find(cities, "ESSEN"), 8));
		rSet.add(new Ticket(Finder.find(cities, "KYIV"), Finder.find(
				cities, "SOCHI"), 8));
		rSet.add(new Ticket(Finder.find(cities, "MADRID"), Finder.find(
				cities, "ZURICH"), 8));
		rSet.add(new Ticket(Finder.find(cities, "BERLIN"), Finder.find(
				cities, "BUCURESTI"), 8));
		rSet.add(new Ticket(Finder.find(cities, "BRUXELLES"), Finder
				.find(cities, "DANZIG"), 9));
		rSet.add(new Ticket(Finder.find(cities, "BERLIN"), Finder.find(
				cities, "ROMA"), 9));
		rSet.add(new Ticket(Finder.find(cities, "ANGORA"), Finder.find(
				cities, "KHARKOV"), 10));
		rSet.add(new Ticket(Finder.find(cities, "RIGA"), Finder.find(
				cities, "BUCURESTI"), 10));
		rSet.add(new Ticket(Finder.find(cities, "ESSEN"), Finder.find(
				cities, "KYIV"), 10));
		rSet.add(new Ticket(Finder.find(cities, "VENEZIA"), Finder
				.find(cities, "CONSTANTINOPLE"), 10));
		rSet.add(new Ticket(Finder.find(cities, "LONDON"), Finder.find(
				cities, "WIEN"), 10));
		rSet.add(new Ticket(Finder.find(cities, "ATHINA"), Finder.find(
				cities, "WILNO"), 11));
		rSet.add(new Ticket(Finder.find(cities, "STOCKHOLM"), Finder
				.find(cities, "WIEN"), 11));
		rSet.add(new Ticket(Finder.find(cities, "BERLIN"), Finder.find(
				cities, "MOSKVA"), 12));
		rSet.add(new Ticket(Finder.find(cities, "AMSTERDAM"), Finder
				.find(cities, "WILNO"), 12));
		rSet.add(new Ticket(Finder.find(cities, "FRANKFURT"), Finder
				.find(cities, "SMOLENSK"), 13));

		return rSet;
	}

	// all routes
	// https://boardgamegeek.com/thread/62863/listing-destination-tickets-and-enumeration-cities

	/**
	 * Returns Set representing all long tickets in the game.
	 * 
	 * The parameter cities must contain references to the actual cities being
	 * used in the game.
	 */
	public static Set<Ticket> makeEuropeLongs(Set<City> cities) {
		
		Set<Ticket> rSet = new HashSet<>();
		
		rSet.add(new Ticket(Finder.find(cities, "LISBOA"), Finder.find(cities, "DANZIG"), 20));
		
		rSet.add(new Ticket(Finder.find(cities, "BREST"), Finder.find(cities, "PETROGRAD"), 20));
		
		rSet.add(new Ticket(Finder.find(cities, "PALERMO"), Finder.find(cities, "MOSKVA"), 20));
		
		rSet.add(new Ticket(Finder.find(cities, "KOBENHAVN"), Finder.find(cities, "ERZURUM"), 21));
		
		rSet.add(new Ticket(Finder.find(cities, "EDINBURGH"), Finder.find(cities, "ATHINA"), 21));
		
		rSet.add(new Ticket(Finder.find(cities, "CADIZ"), Finder.find(cities, "STOCKHOLM"), 21));
		
		return rSet;
	}

	public static Set<Ticket> makeAmericaTickets(Set<City> cities) {
		
		Set<Ticket> rSet = new HashSet<>();

		rSet.add( new Ticket( 	Finder.find( cities, "LOS ANGELES" ), 
				Finder.find( cities, "NEW YORK" ), 
				21 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "DULUTH" ), 
				Finder.find( cities, "HOUSTON" ), 
				8 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "SAULT" ), 
				Finder.find( cities, "Nash" ), 
				8 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "atlanta" ), 
				Finder.find( cities, "NEW YORK" ),
				6 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "portland" ), 
				Finder.find( cities, "nash" ),
				17 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "vancouver" ), 
				Finder.find( cities, "montreal" ),
				20 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Duluth" ), 
				Finder.find( cities, "El Paso" ),
				10 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Toronto" ), 
				Finder.find( cities, "Miami" ),
				10 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Portland" ), 
				Finder.find( cities, "Phoenix" ),
				11 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Dallas" ), 
				Finder.find( cities, "New York" ),
				11 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Calgary" ), 
				Finder.find( cities, "Salt Lake City" ),
				7 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Calgary" ), 
				Finder.find( cities, "Phoenix" ),
				13 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Angeles" ), 
				Finder.find( cities, "Miami" ),
				20 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Winnipeg" ), 
				Finder.find( cities, "Little Rock" ),
				11 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "San Francisco" ), 
				Finder.find( cities, "Atlanta" ),
				17 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Kansas City" ), 
				Finder.find( cities, "Houston" ),
				5 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Angeles" ), 
				Finder.find( cities, "Chicago" ),
				16 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Denver" ), 
				Finder.find( cities, "Pitts" ),
				11 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Boston" ), 
				Finder.find( cities, "Miami" ),
				12 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Santa Fe" ), 
				Finder.find( cities, "Chicago" ),
				9 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Santa Fe" ), 
				Finder.find( cities, "Vancouver" ),
				13 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Montreal" ), 
				Finder.find( cities, "Atlanta" ),
				9 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "New Orleans" ), 
				Finder.find( cities, "Chicago" ),
				7 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Seattle" ), 
				Finder.find( cities, "New York" ),
				22 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Denver" ), 
				Finder.find( cities, "El Paso" ),
				4 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Helena" ), 
				Finder.find( cities, "Angeles" ),
				8 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Winnipeg" ), 
				Finder.find( cities, "Houston" ),
				12 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Montreal" ), 
				Finder.find( cities, "New Orleans" ),
				13 ) );
		
		rSet.add( new Ticket( 	Finder.find( cities, "Sault" ), 
				Finder.find( cities, "Oklahoma" ),
				9 ) );

		rSet.add( new Ticket( 	Finder.find( cities, "Seattle" ), 
				Finder.find( cities, "Angeles" ),
				9 ) );
		
		return rSet;
	}
}
