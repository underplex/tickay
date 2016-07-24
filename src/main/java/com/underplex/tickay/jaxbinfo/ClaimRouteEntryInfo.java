
package com.underplex.tickay.jaxbinfo;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.jaxb.ClaimRouteEntry;
import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.jaxb.TrainType;

/**
 * Immutable wrapper for entry recording the claiming of a route.
 * @author Brandon Irvine
 */
public class ClaimRouteEntryInfo implements PlayEntryInfo{

    private ClaimRouteEntry source;

    /**
     * Constructor used by simulator.
     */
    public ClaimRouteEntryInfo(ClaimRouteEntry source) {
        this.source = source;
    }
    
	public PlayerType getPlayer() {
		return source.getPlayer();
	}

	public PlayType getPlayType() {
		return PlayType.CLAIM_ROUTE;
	}

	public String getRoute() {
		return source.getRoute();
	}

	public List<TrainType> getPayment() {
		return new ArrayList<>( source.getPayment() );
	}
	
	/**
	 * Returns entry recording the tunnel flipping/paying if a tunnel route is being claimed.
	 */
	public TunnelingEntryInfo getTunneling() {
		return source.getTunneling().info();
	}

}
