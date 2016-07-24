package com.underplex.tickay.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.PlayerManagerInfo;
import com.underplex.tickay.player.Player;

/**
 * Manages the player and their seating order in a game of Ticket to Ride.
 * @author Brandon Irvine
 *
 */
public class PlayerManager implements InfoSource<PlayerManagerInfo> {

	private final Map<Player, Integer> playerBySeat;
	private final Map<Integer, Player> seatByPlayer;
	
	private final Game game;
	private final PlayerManagerInfo info;

	public PlayerManager( String expansion, Game game ) {
		this.playerBySeat = new HashMap<Player, Integer>();
		this.seatByPlayer = new HashMap<Integer, Player>();
		this.game = game;
		this.info = new PlayerManagerInfo(this);
		// we ignore expansion info
	}

	public PlayerManagerInfo info() {
		return this.info;
	}

	public Map<Player, Integer> getPlayerMap(){
		return this.playerBySeat;
	}
	
	public void assignSeat(Player player, int seat) {
		this.playerBySeat.put( player, seat );
		this.seatByPlayer.put( seat, player );
	}

	public List<Player> turnOrder() {
		List<Player> rList = new ArrayList<>();
		for ( int i = 1; i <= this.seatByPlayer.size(); i++ )
			rList.add( this.seatByPlayer.get(i) );
		return rList;
	} // end method

	public Player next( Player current ) {
		Player rp = null;
		int currentSeat = playerBySeat.get( current );
		if ( currentSeat == playerBySeat.size() ) {
			rp = this.seatByPlayer.get( 1 );
		} else {
			rp = this.seatByPlayer.get( currentSeat + 1 );
		}
		return rp;
	} // end method
	
	public int size(){
		return this.playerBySeat.size();
	}

}
