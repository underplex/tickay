
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.jaxb.TurnEntry;

/**
 * Immutable wrapper for base class of entry recording a turn.
 * @author Brandon Irvine 
 */
public class TurnEntryInfo {

    protected TurnEntry source;

    public TurnEntryInfo(TurnEntry source) {
        this.source = source;
    }

	/**
	 * Returns turn number.
	 */
    public int getTurn() {
		return source.getTurn();
	}

    /**
     * Returns player that took the turn.
     */
	public PlayerType getPlayer() {
		return source.getPlayer();
	}

	/**
     * Returns category of the player's play.
     */
	public PlayType getPlayType() {
		return source.getPlayType();
	}

    /**
     * Returns the play that this turn recorded.
     */
    public PlayEntryInfo getPlay(){
    	if ( this.getClaimRoute() != null ){
    		return this.getClaimRoute();
    	} else if ( this.getDrawTickets() != null ){
    		return this.getDrawTickets();
    	} else if ( this.getTakeTrains() != null ){
    		return this.getTakeTrains();
    	} else if ( this.getBuildStation() != null ){
    		return this.getBuildStation();
      	}
    	return null;
    }
	
	public ClaimRouteEntryInfo getClaimRoute() {
		return source.getClaimRoute().info();
	}

	public DrawTicketsEntryInfo getDrawTickets() {
		return source.getDrawTickets().info();
	}

	public TakeTrainsEntryInfo getTakeTrains() {
		return source.getTakeTrains().info();
	}

	public BuildStationEntryInfo getBuildStation() {
		return source.getBuildStation().info();
	}

}
