
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.ClaimRouteEntry;

/**
 * Immutable wrapper for entry recording the claiming of a route.
 * <p>
 * This class allows access to info exclusively available in game to a single player.
 * @author Brandon Irvine
 *
 */
public class ClaimRouteEntryInfoMe extends ClaimRouteEntryInfo implements PlayEntryInfo{

    private ClaimRouteEntry source;

    public ClaimRouteEntryInfoMe(ClaimRouteEntry source) {
        super(source);
    }

	/**
	 * Returns entry recording the tunnel flipping/paying if a tunnel route is being claimed.
	 * <p>
	 * Returned entry allows access to info exclusively available in game to a single player.
	 */
	public TunnelingEntryInfoMe getMyTunneling() {
		return source.getTunneling().myInfo();
	}
}
