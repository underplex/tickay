package com.underplex.tickay.game;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.PhaseManagerInfo;

/**
 * Manages phases and turn tracking for a Game of Ticket to Ride.

 * @author Brandon Irvine
 */
public class PhaseManager implements InfoSource<PhaseManagerInfo>{

	private Game game;
	private PhaseType phase;
	private int turn;
	private boolean lastTurn;
	private PhaseManagerInfo info;
	private String expansion;

	public PhaseManager( String expansions, Game game) {
		this.game = game;
		this.expansion = expansions.toUpperCase().trim();
		this.info = new PhaseManagerInfo(this);

		this.phase = PhaseType.FIRST_TURNS;
		this.turn = 0;
		this.lastTurn = false;
	}

	public PhaseManagerInfo info(){
		return this.info;
	}
	
	public void beginGame() {
		this.turn = 1;
	}

	/**
	 * Advances turn 1 forward.
	 */
	public void advanceTurn() {
		turn++;
	}

	public void setLastTurn( boolean lastTurn ) {
		this.lastTurn = lastTurn;
	}

	public void setPhase( PhaseType phase ) {
		this.phase = phase;
	}
	
	public void setTurn(int turn){
		this.turn = turn;
	}

	public Game getGame() {
		return this.game;
	}

	public PhaseType getPhase() {
		return phase;
	}

	/**
	 * Returns number of the current turn or 0 if the concept isn't appropriate at this point.
	 */
	public int getTurn() {
		return turn;
	}

	public boolean isLastTurn() {
		return lastTurn;
	}

	public String getExpansion() {
		return expansion;
	}

	// getters
}
