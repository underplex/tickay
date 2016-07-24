
package com.underplex.tickay.jaxbinfo;

import java.util.ArrayList;
import java.util.List;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.jaxb.TunnelingEntry;

/**
 * Immutable wrapper for entry recording the flipping and payment for tunnel.
 * @author Brandon Irvine 
 */
public class TunnelingEntryInfo {

    protected TunnelingEntry source;

    public TunnelingEntryInfo(TunnelingEntry source) {
        this.source = source;
    }
    
    /**
     * Returns cards turned up by flipping.
     */
	public List<DeckDrawEntryInfo> getAdditionalTurnedUp() {
		return InfoWrapper.wrapToList( source.getAdditionalTurnedUp() );
	}

	/**
	 * Returns number of additional cards required as payment.
	 */
	public int getAdditionalRequired() {
		return source.getAdditionalRequired();
	}

	/**
	 * Returns true iff the player actually paid the additional cost required.
	 */
	public boolean isDoesPay() {
		return source.isDoesPay();
	}

	/**
	 * Returns list of cards spent to pay additional cost, if any.
	 */
	public List<TrainType> getSpentForAdditional() {
		return new ArrayList<>( source.getSpentForAdditional() );
	}

}
