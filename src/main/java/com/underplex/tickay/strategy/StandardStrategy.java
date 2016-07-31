package com.underplex.tickay.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.tickay.info.GameInfo;
import com.underplex.tickay.info.PlayInfo;
import com.underplex.tickay.info.PlayerInfoMe;
import com.underplex.tickay.info.SecondTakePlayInfo;
import com.underplex.tickay.info.TicketChoicePlayInfo;
import com.underplex.tickay.info.TicketInfo;

/**
 * A fairly basic strategy for Ticket to Ride (America).
 * @author Brandon Irvine
 *
 */
public class StandardStrategy implements AmericaStrategy{

	public PlayInfo chooseRegularPlay(GameInfo game, PlayerInfoMe player,
			Set<PlayInfo> plays) {
		// what's a basic way to choose plays?
		// if I can build toward a route for one of my tickets, I should...
		return null;
	}

	public Set<TicketInfo> discardFirstTickets(GameInfo game,
			PlayerInfoMe player, Set<TicketInfo> options) {
		return new HashSet<TicketInfo>();
	}

	public SecondTakePlayInfo chooseSecondTake(GameInfo game,
			PlayerInfoMe player, Set<SecondTakePlayInfo> options) {
		// TODO Auto-generated method stub
		return null;
	}

	public TicketChoicePlayInfo chooseReturnTickets(GameInfo game,
			PlayerInfoMe player, List<TicketChoicePlayInfo> options) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getIdentifier(){
		return this.getClass().getSimpleName();
	}
	
}
