package com.underplex.tickay.info;

import java.util.List;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.player.StationManager;

/**
 * Immutable wrapper for station manager.
 * 
 * @author Brandon Irvine
 *
 */
public class StationManagerInfo {

	public StationManager source;

	/**
	 * Constructor using the mutable counterpart of this instance.
	 */
	public StationManagerInfo(StationManager source) {
		this.source = source;
	}

	/**
	 * Returns location of any cities that are built or empty <code>List</code> if none.
	 */
	public List<CityInfo> built() {
		return InfoWrapper.wrapToList( source.built() );
	}
	
	/**
	 * Returns number of stations still left to play.
	 */
	public int stationsLeft() {
		return source.stationsLeft();
	}

	public String toString() {
		return source.toString();
	}
	
	
	
	
}
