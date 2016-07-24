package com.underplex.tickay.info;

import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.player.Player;

/**
 * Immutable wrapper of a player instance.
 * <p>
 * Note that the information available via this class is information that is common knowledge ("public" knowledge)
 * in the game and not any info restricted to the player himself. For example, instances of this wrapper will never
 * allow access to classes that themselves reveal the cards that the player holds, for example.
 * @author Brandon Irvine
 *
 */
public class PlayerInfo {

	protected Player source;

	/**
	 * Constructor used by simulator.
	 */
	public PlayerInfo(Player source) {
		this.source = source;
	}

	/**
	 * Returns class instance that manages this player's tickets (that is, brown cards worth points at end of game).
	 */
	public TicketHandManagerInfo getTicketHand() {
		return this.source.getTickets().info();
	}

	/**
	 * Returns class instance that manages this player's trains (that is, cards of various colors with trains on them).
	 */
	public TrainHandManagerInfo getTrainHand() {
		return this.source.getTrains().info();
	}

	/**
	 * Returns class instance that manages this player's points.
	 */
	public int getPointCount() {
		return this.source.getPoints().count();
	}

	/**
	 * Returns <code>PlayerType</code> used to identify this player uniquely.
	 * <p>
	 * The value returned will be unique among player in the game because it indicates which seating position the player is in.
	 * <p>
	 * <code>PlayerType.APU</code> is the first player.<p>
	 * <code>PlayerType.BARNEY</code> is the second player.<p>
	 * <code>PlayerType.CARL</code> is the third player.<p>
	 * <code>PlayerType.DUFFMAN</code> is the fourth player.<p>
	 * <code>PlayerType.EDNA</code> is the fifth player.<p>
	 */
	public PlayerType getPlayerType() {
		return this.source.getPlayerType();
	}

	/**
	 * Returns class instance that manages this player's pieces (that is, plastic train tokens).
	 */
	public PieceManagerInfo getPieceCount() {
		return this.source.getPieces().info();
	}
	
	/**
	 * Returns class instance that manages this player's networks (that is, networks of routes and cities).
	 */
	public NetworkManagerInfo getNetworkManager() {
		return this.source.getNetworks().info();
	}

	/**
	 * Returns class instance that manages this player's claimed routes (that is, segments between cities).
	 */
	public RouteManagerInfo getRouteManager() {
		return this.source.getRoutes().info();
	}
}
