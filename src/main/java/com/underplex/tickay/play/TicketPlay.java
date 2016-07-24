package com.underplex.tickay.play;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.game.Ticket;
import com.underplex.tickay.info.TicketChoicePlayInfo;
import com.underplex.tickay.info.TicketPlayInfo;
import com.underplex.tickay.jaxb.DrawTicketsEntry;
import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.jaxb.StrategyErrorEntry;
import com.underplex.tickay.jaxb.StrategyErrorType;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Picker;

/**
 * An instance represents the option to look at tickets and keep some or all.
 * <p>
 * An instantiation merely represents that this can be done legally (in the usual case when there are any tickets in the ticket deck). The choice to look at 
 * tickets form the ticket deck can't be any more specific, so this choice has no details. (If it's not present, it's not legal. If it is, choosing it
 * means looking at tickets.)  
 * @author irvin_000
 *
 */
public class TicketPlay implements Play {

	private Game game;
	private Player player;
	private TicketPlayInfo info;

	public TicketPlay( Game game, Player player ) {
		this.game = game;
		this.player = player;
		this.info = new TicketPlayInfo(this);
	}
	
	public String toString(){
		return "PLAY: draw 3 tickets";
	}
	
	public TicketPlayInfo info() {
		return info;
	}

	public PlayType getPlayType() {
		return PlayType.DRAW_TICKETS;
	}

	public DrawTicketsEntry resolve( Game game, Player player ) { 
		DrawTicketsEntry entry = new DrawTicketsEntry();
		entry.setPlayer(player.getPlayerType());

		Set<Ticket> draws = new HashSet<>();
		List<Ticket> shorts = game.getTickets().getShorts(); // convenience

		// draw 3 tickets or the number of tickets in ticket deck, whichever is less
		for (int i = 1; i <= 3; i++) {
			if (shorts.size() > 0) {
				draws.add( shorts.get(0) );
				shorts.remove(0);
			}
		}

		if ( draws.size() == 0 ) {	// if ticket pile is actually empty
			entry.setNoTicketsToTake(true);
		} else {
			TicketChoicePlayInfo choiceInfo = null;
			// 1 <= draws <= 3 at this point
			Set<TicketChoicePlay> options = PlayFinder.findTicketChoicePlays( game, player, draws );
			List<TicketChoicePlayInfo> optionsInfo = InfoWrapper.wrapToList( options );

			if ( optionsInfo.size() == 1 ){
				choiceInfo = optionsInfo.get(0);
			} else {
			
				choiceInfo = player.getStrategy().chooseReturnTickets( game.info(), player.myInfo(), optionsInfo );

				// validate choice
				if ( !optionsInfo.contains(choiceInfo) ) {
					StrategyErrorEntry errorEntry = new StrategyErrorEntry();
					errorEntry.setStrategyClass(player.getStrategy().getClass().getSimpleName());
					errorEntry.setStrategyError(StrategyErrorType.TICKET_CHOICE);
					entry.setError(errorEntry);
	
					// get random option that was legal to replace illegal one
					choiceInfo = Picker.selectRandom( optionsInfo );
				}
			}
			TicketChoicePlay choice = InfoWrapper.unwrap( options, choiceInfo );
		
			
			// do all the updates to the entry that need to be done

			choice.choose( game, player );
			
			for ( Ticket t : choice.getKeep() ) {
				entry.getKeptTicket().add( t.toString() );
			}

			for ( Ticket t : choice.getPutBack() ){
				entry.getReturnedTicket().add( t.toString() );
			}
			
		} // end if-else draws.size() > 0
		return entry;
	} // end method

}
