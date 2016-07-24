package com.underplex.tickay.game;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class for making sets of cities depending on the game being used.
 * <p>
 * No public constructor is provided because the class isn't intended to be instantiated.
 * @author Brandon Irvine
 *
 */
public class CityFactory {

	private CityFactory(){
		// don't instantiate this
	}
	
	/**
	 * Returns set containing names of all the cities for the given game or expansion in roughly east-west order.
	 * <p>
	 * The names are the names as given on the board.
	 * <p>
	 * As of 11-Nov-2015, only supports the Europe expansion (and not the base).
	 * 
	 * @param expansions		String of the game or expansion
	 * @return				Set of city names for the <code>expansion</code>
	 */
	public static Set<City> makeCities( String expansions ){
		
		Set<City> rSet = new HashSet<>();
		
		if (expansions.toUpperCase().contains( "EUROPE" ) ){
			rSet.add(new City("Cadiz",'b',12));
			rSet.add(new City("Lisboa",'a',11));	
			rSet.add(new City("Barcelona",'c',11));			
			rSet.add(new City("Madrid",'b',11));
		
			rSet.add(new City("Edinburgh",'c',2));
			rSet.add(new City("Brest",'b',7));			
			rSet.add(new City("Dieppe",'c',6));
			rSet.add(new City("Paris",'d',7));
			rSet.add(new City("Bruxelles",'d',6));
		
			rSet.add(new City("London",'c',5));
			
			rSet.add(new City("Amsterdam",'d',5));
			rSet.add(new City("Pamplona",'c',9));
			rSet.add(new City("Zurich",'e',8));
			rSet.add(new City("Marseille",'e',9));
			
			rSet.add(new City("Berlin",'f',5));
			rSet.add(new City("Essen",'e',5));
			rSet.add(new City("Frankfurt",'e',6));
			rSet.add(new City("Munchen",'f',7));
			rSet.add(new City("Venezia",'f',8));
			rSet.add(new City("Roma",'f',9));
			
			rSet.add(new City("Kobenhavn",'f',3));
			rSet.add(new City("Palermo",'g',12));
	
			rSet.add(new City("Stockholm",'g',1  ));
			rSet.add(new City("Wien",'g',7  ));
			rSet.add(new City("Zagreb",'g',8  ));
			rSet.add(new City("Brindisi",'g',10 ));

			rSet.add(new City("Danzig",'h',4  ));
			rSet.add(new City("Budapest",'h',7  ));
			rSet.add(new City("Sarajevo",'h',9  ));
	
			rSet.add(new City("Riga",'i',3  ));
			rSet.add(new City("Warszawa",'h',5  ));
			rSet.add(new City("Sofia",'i',9  ));
			rSet.add(new City("Athina",'i',11 ));
			
			rSet.add(new City("Wilno",'j',5  ));
			rSet.add(new City("Bucuresti",'j',8  ));

			rSet.add(new City("Kyiv",'j',6  ));
			rSet.add(new City("Constantinople",'j',10 ));
			rSet.add(new City("Smyrna",'j',12 ));

			rSet.add(new City("Smolensk",'k',5  ));
			rSet.add(new City("Sevastopol",'k',8  ));
			rSet.add(new City("Angora",'k',11 ));

			rSet.add(new City("Petrograd",'k',2  ));
			rSet.add(new City("Moskva",'L',5  ));
			rSet.add(new City("Kharkov",'L',6 )); // this is kind of a fudge but keep this out of the same zone as Rostov and is logical
			rSet.add(new City("Rostov",'L',7  ));
			rSet.add(new City("Erzurum",'L',8  ));
			rSet.add(new City("Sochi",'L',11 ));
			
		} else if ( expansions.toUpperCase().contains( "AMERICA" ) ){
		
			rSet.add(new City("Vancouver",'a',1 ));	
			rSet.add(new City("Seattle",'a',2 ));
			rSet.add(new City("Portland",'a',3 ));
			rSet.add(new City("San Francisco",'a',6 ));
			rSet.add(new City("Los Angeles",'a',8 ));
			rSet.add(new City("Calgary",'b',1 ));
			rSet.add(new City("Helena",'c',3 ));
			rSet.add(new City("Salt Lake City",'c',6 ));
			
			rSet.add(new City("Las Vegas",'b',7 ));
			rSet.add(new City("Phoenix",'c',8 ));
			rSet.add(new City("Winnipeg",'d',1 ));
			rSet.add(new City("Denver",'d',6 ));
			rSet.add(new City("Santa Fe",'d',7 ));
			rSet.add(new City("El Paso",'d',9 ));
			rSet.add(new City("Duluth",'e',3 ));
			rSet.add(new City("Omaha",'e',5 ));
			rSet.add(new City("Kansas City",'e',6 ));
			rSet.add(new City("Oklahoma City",'e',7 ));
			rSet.add(new City("Dallas",'e',8 ));
			rSet.add(new City("Houston",'f',9 ));

			rSet.add(new City("Sault St. Marie",'g',2 ));
			rSet.add(new City("Chicago",'g',4 ));
			rSet.add(new City("Saint Louis",'f',6 ));
			rSet.add(new City("Little Rock",'f',7 ));
			rSet.add(new City("New Orleans",'g',9 ));

			rSet.add(new City("Toronto",'h',2 ));
			rSet.add(new City("Pittsburgh",'h',4 ));
			rSet.add(new City("Nashville",'g',6 ));
			rSet.add(new City("Atlanta",'h',7 ));
			
			rSet.add(new City("Montreal",'i',1 ));
			rSet.add(new City("Boston",'i',2 ));
			rSet.add(new City("New York",'i',3 ));
			rSet.add(new City("Washington",'i',5 ));
			rSet.add(new City("Raleigh",'h',6 ));
			rSet.add(new City("Charleston",'i',7 ));
			rSet.add(new City("Miami",'i',9 ));

		}
		return rSet;
	}

}
