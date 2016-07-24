package com.underplex.tickay.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.infowrap.InfoSource;
import com.underplex.infowrap.MyInfoSource;
import com.underplex.tickay.game.Ticket;
import com.underplex.tickay.info.TicketHandManagerInfo;
import com.underplex.tickay.info.TicketHandManagerInfoMe;
import com.underplex.tickay.jaxb.TicketPointsEntry;

/**
 * Represents the hand of a player.
 */
public class TicketHandManager implements InfoSource<TicketHandManagerInfo>, MyInfoSource<TicketHandManagerInfoMe>{

	private final Player player;
	private final Set<Ticket> tickets;
	private final TicketHandManagerInfo info;
	private final TicketHandManagerInfoMe myInfo;	
	
	public TicketHandManager( Player player ) {
		this.tickets = new HashSet<>();
		this.player = player;
		this.info = new TicketHandManagerInfo( this );
		this.myInfo = new TicketHandManagerInfoMe( this );
	}

	public TicketHandManagerInfo info(){
		return this.info;
	}

	public TicketHandManagerInfoMe myInfo(){
		return this.myInfo;
	}
	
	public Set<Ticket> getTickets() {
		return tickets;
	}

	public Player getPlayer() {
		return player;
	}

	public void addTicket( Ticket ticket ) {
		this.tickets.add(ticket);
	}

	/**
	 * Player loses <code>ticket</code>.
	 * 
	 * @param ticket
	 */
	public void lose( Ticket ticket ) {
		this.tickets.remove( ticket );
	}

	/**
	 * Returns TicketPointsEntry instances to report on the points earned or
	 * lost from tickets in the player's current state.
	 */
	public List<TicketPointsEntry> makeEntries() {
		List<TicketPointsEntry> rList = new ArrayList<>();
		TicketPointsEntry entry;

		for (Ticket ticket : tickets) {
			entry = new TicketPointsEntry();
			entry.setRoute( ticket.getCityPair().toString() );
			entry.setPoints( ticket.getPoints() );

			if ( player.getNetworks().canConnect( ticket.getCityPair() ) ) {
				entry.setCompleted( true );
			} else {
				entry.setCompleted( false );
			} // end if-else
			
			rList.add(entry);
		}
		return rList;
	}

}
