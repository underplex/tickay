
package com.underplex.tickay.jaxbinfo;

import java.util.List;

import com.underplex.tickay.jaxb.BuildStationEntry;
import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.jaxb.TrainType;

/**
 * Immutable wrapper for class recording the building of a station.
 * @author Brandon Irvine
 */
public class BuildStationEntryInfo implements PlayEntryInfo {

    private BuildStationEntry source;

    /**
     * Constructor used by simulator.
     */
    public BuildStationEntryInfo(BuildStationEntry source) {
        this.source = source;
    }
    
	public PlayType getPlayType() {
		return PlayType.BUILD_STATION;
	}
	
	public PlayerType getPlayer() {
		return source.getPlayer();
	}

	/**
	 * Returns 1-3 depending on if this is the first, second, or third station built by the player.
	 */
	public int getStationNumber() {
		return source.getStationNumber();
	}

	/**
	 * Returns station location.
	 */
	public String getStationLocation() {
		return source.getStationLocation();
	}

	/**
	 * Returns the cards that paid for the station.
	 */
	public List<TrainType> getSpentForStation() {
		return source.getSpentForStation();
	}

}
