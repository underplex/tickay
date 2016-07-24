package com.underplex.tickay.strategy;

import java.util.List;
import java.util.Set;

import com.underplex.tickay.info.GameInfo;
import com.underplex.tickay.info.PlayInfo;
import com.underplex.tickay.info.PlayerInfoMe;
import com.underplex.tickay.info.SecondTakePlayInfo;
import com.underplex.tickay.info.TicketChoicePlayInfo;
import com.underplex.tickay.info.TicketInfo;


/**
 * Interface representing a strategy in Ticket to Ride.
 * <p>
 * A simulator will use the implemented methods to make all decisions that a player would during the course of a game.
 * <p>
 * At the same time, the game simulator will provide complete game state information in the form of parameters passed to the methods.
 * The parameters passed take the form of wrappers that provide information.
 * <p>
 * Objects with suffix <code>-Info</code> might need to be cast to a subtype to access all the information. For example, a <code>game</code> parameter might 
 * be given as <code>PlayerInfo</code> but also could be a subtype of <code>EuroPlayerInfo</code>; <code>PlayerInfo</code> doesn't have any information about stations,
 * where <code>EuroPlayerInfo</code> will.
 * @author Brandon Irvine
 *
 */
public abstract interface Strategy {

	  /**
	   * Returns the choice of what to play on a regular turn.
	   * @param	game	<code>GameInfo</code> representing the game being played
	   * @param	player	<code>PlayerInfoMe</code> representing the player making the decision
	   * @param plays	<code>Set</code> of <code>PlayInfo</code>s representing all legal play options
	   */
	  PlayInfo chooseRegularPlay( GameInfo game, PlayerInfoMe player, Set<PlayInfo> plays );
	
	  /**
	   * Returns tickets to discard at the beginning of the game.
	   * <p>
	   * Set returned must be empty, size() == 1, or size() == 2 (by game rules).
	   * <p>
	   * Any other return will cause an error that is logged by the game, and the discard choice will be made arbitrarily.
	   * @param	game	<code>GameInfo</code> representing the game being played
	   * @param	player	<code>PlayerInfoMe</code> representing the player making the decision
	   * @param options	<code>Set</code> of <code>TicketInfo</code>s representing all the legal combinations of keeping/discarding
	   */
	  Set<TicketInfo> discardFirstTickets( GameInfo game, PlayerInfoMe player, Set<TicketInfo> options );	
	
	  /**
	   * Returns follow-up train card to pick when a second card can be picked up given legal options.
	   * @param	game	<code>GameInfo</code> representing the game being played
	   * @param	player	<code>PlayerInfoMe</code> representing the player making the decision
	   * @param	options <code>List</code> of <code>SecondTakePlayInfo</code>s representing all legal take play
	    */
	  SecondTakePlayInfo chooseSecondTake( GameInfo game, PlayerInfoMe player, Set<SecondTakePlayInfo> options );
	  
	  /**
	   * Returns instance representing the follow-up decision for drawing tickets that returns unwanted tickets.
	   * @param	game	<code>GameInfo</code> representing the game being played
	   * @param	player	<code>PlayerInfoMe</code> representing the player making the decision
	   * @param	options <code>List</code> of <code>TicketChoicePlayInfo</code>s representing all legal combinations of keeping/returning tickets
	   */
	  TicketChoicePlayInfo chooseReturnTickets( GameInfo game, PlayerInfoMe player, List<TicketChoicePlayInfo> options);
}