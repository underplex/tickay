package com.underplex.tickay.info;

import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.player.Player;

/**
 * Immutable wrapper of a player instance.
 * <p>
 * Note that the information available via this class and some of its members is sometimes secret and not available to the other player
 * in the game.
 * @author Brandon Irvine
 *
 */
public abstract class PlayerInfoMe {

	protected final Player source;
	
	/**
	 * Constructor used by simulator.
	 */
	public PlayerInfoMe(Player source) {
		this.source = source;
	}
	
	/**
	 * Returns ticket hand with access to player's secret knowledge.
	 */
	public TicketHandManagerInfoMe getMyTicketHand() {
		return this.source.getTickets().myInfo();
	}

	/**
	 * Returns train hand with access to player's secret knowledge.
	 */
	public TrainHandManagerInfoMe getMyTrainHand() {
		return this.source.getTrains().myInfo();
	}

	/**
	 * Returns the name of the strategy class this player uses to make decisions.
	 */
	public String getMyStrategy() {
		return this.source.getStrategy().getClass().getSimpleName();
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
