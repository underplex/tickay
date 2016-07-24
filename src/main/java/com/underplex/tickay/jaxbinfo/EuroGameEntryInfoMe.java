
package com.underplex.tickay.jaxbinfo;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.jaxb.EuroGameEntry;
import com.underplex.tickay.jaxb.FirstDiscardEntry;
import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.jaxb.TurnEntry;

/**
 * Immutable wrapper for entry recording game of Ticket to Ride Europe.
 * <p>
 * This class allows access to info exclusively available in game to a single player.
 * @author Brandon Irvine
 *
 */
public class EuroGameEntryInfoMe extends EuroGameEntryInfo{

    private PlayerType player;

    public EuroGameEntryInfoMe(EuroGameEntry source, PlayerType player) {
    	super(source);
        this.player = player;
    }

    /**
     * Returns entries recording turns with info exclusive to a player.
     */
	public List<TurnEntryInfoMe> getMyTurns() {
		List<TurnEntryInfoMe> rList = new ArrayList<>();
		for ( TurnEntry turn : source.getTurn() )
			if ( turn.getPlayer() == this.player )
				rList.add( turn.myInfo() );
		
		return rList;
	}

	/**
     * Returns entries recording first discards with info exclusive to a player.
     */
	public FirstDiscardEntryInfoMe getMyFirstDiscard() {
		for ( FirstDiscardEntry entry : source.getFirstDiscard() )
			if ( entry.getPlayer() == this.player )
				return entry.myInfo();
		return null;
	}
	
}
