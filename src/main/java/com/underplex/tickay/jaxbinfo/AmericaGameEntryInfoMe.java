
package com.underplex.tickay.jaxbinfo;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.jaxb.FirstDiscardEntry;
import com.underplex.tickay.jaxb.GameEntry;
import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.jaxb.TurnEntry;

/**
 * Immutable wrapper for entry recording game of Ticket to Ride America (aka base version).
 * <p>
 * This class allows access to info exclusively available in game to a single player.
 * @author Brandon Irvine
 *
 */
public class AmericaGameEntryInfoMe extends AmericaGameEntryInfo{

    private PlayerType player;

    /**
     * Constructor used by simulator.
     */
    public AmericaGameEntryInfoMe(GameEntry source, PlayerType player) {
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
