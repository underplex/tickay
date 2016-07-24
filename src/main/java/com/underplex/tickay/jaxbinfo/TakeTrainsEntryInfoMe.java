
package com.underplex.tickay.jaxbinfo;

import com.underplex.tickay.jaxb.TakeTrainsEntry;

/**
 * Immutable wrapper for entry recording a turn where a player takes train cards.
 * <p>
 * This class allows access to info exclusively available in game to a single player.
 * @author Brandon Irvine 
 */
public class TakeTrainsEntryInfoMe extends TakeTrainsEntryInfo implements PlayEntryInfo {

    public TakeTrainsEntryInfoMe(TakeTrainsEntry source) {
        super(source);
    }
	
	public SingleTakeEntryInfoMe getTake1() {
		return source.getTake1().myInfo();
	}

	public SingleTakeEntryInfoMe getTake2() {
		return source.getTake2().myInfo();
	}

}
