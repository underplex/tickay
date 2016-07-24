package com.underplex.tickay.info;

import com.underplex.tickay.game.Game;

/**
 * Subclass is an immutable wrapper for a Ticket to Ride game.
 * @author Brandon Irvine
 *
 */
public abstract class GameInfo {

	protected Game source;

	public GameInfo(Game source) {
		this.source = source;
	}

	/**
	 * Returns immutable phase manager.
	 * 
	 */
	public PhaseManagerInfo getPhaseManager() {
		return this.source.getPhases().info();
	}

	/**
	 * Returns immutable player manager.
	 * 
	 */
	public PlayerManagerInfo getPlayerManager() {
		return this.source.getPlayers().info();
	}

	/**
	 * Returns immutable board manager.
	 * 
	 */
	public BoardManagerInfo getBoardManager() {
		return this.source.getBoard().info();
	}
	
	/**
	 * Returns immutable ticket manager.
	 * 
	 */
	public TicketManagerInfo getTicketManager() {
		return this.source.getTickets().info();
	}

	/**
	 * Returns immutable train manager.
	 * 
	 */
	public TrainManagerInfo getTrainManager() {
		return this.source.getTrains().info();
	}
	
}
