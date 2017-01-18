package com.underplex.tickay.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.TicketManagerInfo;

public class TicketManager implements InfoSource<TicketManagerInfo> {

	private final Game game;
	private final List<Ticket> longs;
	private final List<Ticket> shorts;
	private final List<Ticket> oblivion;
	private final TicketManagerInfo managerInfo;

	/**
	 * Parameter cities must be the actual instances of cities being used with
	 * these tickets.
	 * @param expansions 	<code>String</code> containing version and expansions used
	 * @param game			<code>Game</code> using this ticket manager
	 * @param cities		set of <code>City</code> instances used by the game
	 */
	public TicketManager( String expansions, Game game, Set<City> cities ) {
		this.game = game;
		this.longs = new ArrayList<>();
		this.shorts = new ArrayList<>();
		this.oblivion = new ArrayList<Ticket>();
		this.managerInfo = new TicketManagerInfo(this);
		
		// System.out.println( expansions.toUpperCase() );
		
		if ( expansions.toUpperCase().contains( "EUROPE" ) ){
			this.longs.addAll( TicketFactory.makeEuropeLongs( cities ) );
			Collections.shuffle( this.longs );
			this.shorts.addAll ( TicketFactory.makeEuropeShorts( cities ) );
			Collections.shuffle( this.shorts );
		} else if ( expansions.toUpperCase().contains( "AMERICA" ) ){
			this.shorts.addAll ( TicketFactory.makeAmericaTickets( cities ) );
			Collections.shuffle( this.shorts );
		}
		
		//System.out.println( "after check of expansions..." );
	}

	public Game getGame() {
		return game;
	}

	public List<Ticket> getLongs() {
		return longs;
	}

	public List<Ticket> getShorts() {
		return shorts;
	}

	public List<Ticket> getOblivion() {
		return oblivion;
	}
	
	public TicketManagerInfo info(){
		return managerInfo;
	}
	// getters, setters

}
