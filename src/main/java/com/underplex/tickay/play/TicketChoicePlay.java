package com.underplex.tickay.play;

import java.util.HashSet;
import java.util.Set;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.game.Ticket;
import com.underplex.tickay.info.TicketChoicePlayInfo;
import com.underplex.tickay.player.Player;

/**
 * Represents a legal option for making the follow-up decision to return certain
 * tickets to the shorts deck.
 * 
 * @author irvin_000
 *
 */
public class TicketChoicePlay implements InfoSource<TicketChoicePlayInfo> {

	private Set<Ticket> keep;
	private Set<Ticket> putBack;
	private TicketChoicePlayInfo info;

	/**
	 * Constructor for play taking the tickets looked at and two possible
	 * returns.
	 * 
	 * @param look
	 * @param ticket1
	 * @param ticket2
	 */
	public TicketChoicePlay(Set<Ticket> look, Ticket ticket1, Ticket ticket2) {
		this.keep = new HashSet<>( look );
		this.putBack = new HashSet<Ticket>(); 
		if ( ticket1 != null ) {
			this.keep.remove( ticket1 );
			this.putBack.add( ticket1);
		}
		if ( ticket2 != null ) {
			this.keep.remove( ticket2 );
			this.putBack.add( ticket2 );
		}
	
		this.info = new TicketChoicePlayInfo(this);
	}
	
	public String toString(){
		return "PLAY CHOICE: keep " + keep + " and put back " + putBack;
	}

	public TicketChoicePlayInfo info() {
		return info;
	}

	public Set<Ticket> getKeep() {
		return keep;
	} 
	
	public Set<Ticket> getPutBack(){
		return putBack;
	}

	/**
	 * Returns true if the parameter is a TicketChoicePlay with the same tickets to keep and the same tickets to put back.
	 */
	public boolean equals( Object obj ){

		if ( obj instanceof TicketChoicePlay ){
			TicketChoicePlay other = (TicketChoicePlay)obj;
			if ( this.keep.equals( other.getKeep() ) && this.putBack.equals( other.putBack) )
				return true;
		}
		return false;
	}
	
	public int hashCode(){
		return ( keep.hashCode() - putBack.hashCode() );
	}	

	/**
	 * Changes game state to reflect this choice.
	 * 
	 * @param game 	EuroGame that this choice is in the context of
	 * @param player	EuroPlayer making this choice
	 */
	public void choose( Game game, Player player){
		for ( Ticket tic : this.keep )
			player.getTickets().addTicket( tic );
			
		for ( Ticket tic : this.putBack )
			game.getTickets().getShorts().add( tic );
		
	}


}
