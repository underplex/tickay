package com.underplex.tickay.game;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.CityInfo;
import com.underplex.tickay.info.CityPairInfo;

/**
 * Represents a pairing of any two cities on the board. * 
 */
public class CityPair implements InfoSource<CityPairInfo> {

	private City city1;
	private City city2;
	private CityPairInfo info;

	/**
	 * Constructor places one city before other based roughly on which is further west or north.
	 */
	public CityPair( City city1, City city2 ) {
		
		if ( CityInfo.isOrdered( city1.info(), city2.info() ) ){
			this.city1 = city1;
			this.city2 = city2;
		} else {
			this.city1 = city2;
			this.city2 = city1;
		}	
		
		this.info = new CityPairInfo(this);
	}
	
	/**
	 * Constructor that copies the cities of <code>original</code>.
	 * @param original
	 */
	public CityPair( CityPair original ){
		this( original.getCity1(), original.getCity2() );
	}

	public City getCity1() {
		return city1;
	}

	public City getCity2() {
		return city2;
	}

	public String toString(){
		return city1.getName() + "~" + city2.getName();
	}
	
	/**
	 * Returns city that isn't the parameter city, or null if the parameter city
	 * isn't one of the cities.
	 */
	public City getOther(City city) {
		if (city == city1) {
			return city2;
		} else if (city == city2) {
			return city1;
		} // end if-else
		return null;
	} // end method

	/**
	 * Returns true if parameter city is a city in this pair.
	 */
	public boolean contains( City city ) {
		return ( this.city1.equals( city ) || this.city2.equals( city ) );
	} // end method
	
	/**
	 * Returns true if either city in <code>pair</code> is present in this <code>CityPair</code>.
	 */
	public boolean containsEither( CityPair pair ){
		boolean rBool = false;
		if ( this.city1 == pair.getCity1() || this.city1 == pair.getCity2() )
			rBool = true;
		if ( this.city2 == pair.getCity1() || this.city2 == pair.getCity2() )
			rBool = true;
		
		return rBool;
	}

	public CityPairInfo info() {
		return this.info;
	} // end method

	/**
	 * Returns true only if this references the same cities, but doesn't take into account the order of them.
	 */
	public boolean equals( Object obj ) {
		boolean rBool = true;
		if ( obj == null ){
			rBool = false;
		} else if ( obj instanceof CityPair ){
			CityPair other = (CityPair)obj;

			if ( !this.city1.equals( other.getCity1() ) && !this.city1.equals( other.getCity2() ))
				rBool = false;
			if ( !this.city2.equals( other.getCity1() ) && !this.city2.equals( other.getCity2() ))
				rBool = false;
		} else {
			rBool = false;
		}
		return rBool;
	}
	
	public int hashCode(){
		return this.city1.hashCode() * this.city2.hashCode();
	}
	// getters and setters
} // end class

