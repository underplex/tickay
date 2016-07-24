package com.underplex.tickay.info;

import com.underplex.tickay.game.CityPair;

/**
 * Immutable wrapper for a pair of cities.
 * <p>
 * This pairing of cities doesn't represent anything directly in the game; it's
 * just a simple way to indicate two cities at once.
 * <p>
 * Unlike other immutable wrappers classes, the relationship between this class and the mutable counterpart is not intended to be 1-to-1, since
 * many references to pairs of cities can be made in analyzing the game state.
 */
public class CityPairInfo {

	private final CityInfo city1;
	private final CityInfo city2;

	/**
	 * Constructor always arranges it so that city1 has a lower location value (so it will appear with same name as printed ticket in game).
	 */
	public CityPairInfo( CityInfo city1, CityInfo city2 ) {
		
		if ( CityInfo.isOrdered(city1, city2) ){
			this.city1 = city1;
			this.city2 = city2;
		} else {
			this.city1 = city2;
			this.city2 = city1;
		}	
	}
	
	/**
	 * Constructor used by simulator.
	 */
	public CityPairInfo(CityPair source) {
		this( source.getCity1().info(), source.getCity2().info() );	
	}
	
	/**
	 * Returns the city in this pair that isn't the parameter city.
	 * <p>
	 * Returns <code>null</code> if the parameter city isn't in this pair. Undefined behavior if both cities are the same one.
	 */
	public CityInfo getOther(CityInfo city) {
		if (city.equals( city1) ) {
			return this.city2;
		} else if ( city.equals(city2)) {
			return this.city1;
		}
		return null;
	} // end getOther

	/**
	 * Returns true if parameter city is a city in this pair.
	 */
	public boolean contains(CityInfo city) {
		return (city.equals( city1 ) || city.equals( city2 ) );
	}
	
	/**
	 * Returns a city arbitrarily designated first in this pair.
	 * @return one city of the pair labeled first
	 */
	public CityInfo getCity1() {
		return city1;
	}
	
	/**
	 * Returns a city arbitrarily designated second in this pair.
	 * @return one city of the pair labeled second
	 */
	public CityInfo getCity2() {
		return city2;
	}

	public String toString(){
		return city1.getName() + "~" + city2.getName();
	}
	
	/**
	 * Returns <code>true</code> if either of the parameter pair is including in this pair.
	 * @param pair	pair to be examined
	 * @return boolean whether either of the pair is in this pair
	 */
	public boolean containsEither(CityPairInfo pair) {
		return this.contains( pair.getCity1() ) || this.contains( pair.getCity2() );
	}
	
	public boolean equals(Object obj){
		boolean rBool = true;
		
		if ( obj == null ){
			rBool = false;
		} else if ( obj instanceof CityPairInfo ){
			CityPairInfo other = (CityPairInfo)obj;
			
			if ( other.getCity1().getName().toUpperCase() != this.getCity1().getName().toUpperCase() )
				rBool = false;

			if ( other.getCity2().getName().toUpperCase() != this.getCity2().getName().toUpperCase() )
				rBool = false;
		} else {
			rBool = false;
		}
		
		return rBool;
		
	}
	
	/**
	 * Returns hashCode that will generally be different than the hashCode for the equivalent unwrapped, mutable version of this class.
	 */
	public int hashCode(){
		return this.city1.getName().hashCode() * this.city1.getName().hashCode() * 7;
	}
}