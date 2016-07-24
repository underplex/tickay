
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.jaxb.StationChoiceEntry;
/**
 * Immutable wrapper for entry recording what route the player chose to cover with a station.
 * <p>
 * @author Brandon Irvine 
 */
public class StationChoiceEntryInfo {

    private StationChoiceEntry source;

    /**
     * Constructor used by simulator.
     */
    public StationChoiceEntryInfo(StationChoiceEntry source) {
        this.source = source;
    }

    /**
     * Returns player who made the decision.
     */
	public PlayerType getPlayer() {
		return source.getPlayer();
	}

	/**
	 * Returns the city where the station is.
	 */
	public String getStationCity() {
		return source.getStationCity();
	}

	/**
	 * Returns the route chosen.
	 */
	public String getChosenRoute() {
		return source.getChosenRoute();
	}

}
