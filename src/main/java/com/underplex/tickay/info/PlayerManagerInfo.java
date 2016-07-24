package com.underplex.tickay.info;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.game.PlayerManager;
import com.underplex.tickay.player.Player;

/**
 * Immutable wrapper for class managing all the players in a game.
 * @author Brandon Irvine
 *
 */
public class PlayerManagerInfo {

	private PlayerManager source;
	
	/**
	 * Constructor used by simulator.
	 */
	public PlayerManagerInfo(PlayerManager source) {
		this.source = source;
	}
	
	/**
	 * Returns list of all players in order of their seating.
	 */
	public List<PlayerInfo> turnOrder() {
		List<PlayerInfo> rList = new ArrayList<>();
		for ( Player player : source.turnOrder() )
			rList.add( player.info() );
		
		return rList;
	}

}
