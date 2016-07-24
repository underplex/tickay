
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.TurnEntry;

/**
 * Immutable wrapper for base class of entry recording a turn.
 * <p>
 * This class allows access to info exclusively available in game to a single player.
 * @author Brandon Irvine
 */
public class TurnEntryInfoMe extends TurnEntryInfo{

    public TurnEntryInfoMe(TurnEntry source) {
    	super(source);
    }

    /**
     * Returns the play that this turn recorded.
     * <p>
     * You will probably have to case the returned object to a <code>-InfoMe</code> object to actually access its information.
     */
    public PlayEntryInfo getMyPlay(){
    	if ( this.getMyClaimRoute() != null ){
    		return this.getMyClaimRoute();
    	} else if ( this.getMyDrawTickets() != null ){
    		return this.getMyDrawTickets();
    	} else if ( this.getMyTakeTrains() != null ){
    		return this.getMyTakeTrains();
    	// build station has no private information about it, so it has no ~InfoMe counterpart
    	} else if ( this.getBuildStation() != null ){
    		return this.getBuildStation();
      	}
    	return null;
    }
    
	public ClaimRouteEntryInfoMe getMyClaimRoute() {
		if ( source.getClaimRoute() != null )
			return source.getClaimRoute().myInfo();
			
		return null;
	}

	public DrawTicketsEntryInfoMe getMyDrawTickets() {
		if ( source.getDrawTickets() != null )
			return source.getDrawTickets().myInfo();
		
		return null;
	}

	public TakeTrainsEntryInfoMe getMyTakeTrains() {
		if ( source.getTakeTrains() != null )
			return source.getTakeTrains().myInfo();
		
		return null;
	}
}
