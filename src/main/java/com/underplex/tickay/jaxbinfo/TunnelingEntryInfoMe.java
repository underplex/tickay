
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.TunnelingEntry;

/**
 * Immutable wrapper for entry recording the flipping and payment for tunnel.
 * <p>
 * This class allows access to info exclusively available in game to a single player.
 * @author Brandon Irvine 
 */
public class TunnelingEntryInfoMe extends TunnelingEntryInfo {

    public TunnelingEntryInfoMe(TunnelingEntry source) {
        super(source);
    }

    /**
     * Returns true if player could have paid the cost.
     */
    public boolean isCanPay() {
		return source.isCanPay();
	}

}
