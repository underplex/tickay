package com.underplex.tickay.strategy;

import java.util.Set;

import com.underplex.tickay.info.CityInfo;
import com.underplex.tickay.info.EuroGameInfo;
import com.underplex.tickay.info.PaymentInfo;
import com.underplex.tickay.info.PlayerInfoMe;
import com.underplex.tickay.info.RouteInfo;

/**
 * A strategy for the game Ticket to Ride Europe.
 * <p>
 * A simulator will use the implemented methods to make all decisions that a player would in during the course of a game.
 * <p>
 * At the same time, the game simulator will provide complete game state information in the form of parameters passed to the methods.
 * The parameters passed take the form of wrappers that provide information.
 * @author Brandon Irvine
 *
 */
public interface EuroStrategy extends Strategy{
   
  /**
   * Returns the decision for what route to use station to extend over.
   * <p>
   * @param	game	<code>EuroGameInfo</code> representing the game being played
   * @param	player	<code>PlayerInfoMe</code> representing the player making the decision
   * @param city	<code>CityInfo</code> city where the station being considered is located
   * @param routes	<code>Set</code> of <code>RouteInfo</code>s representing all legal routes that can be extended
   */
  RouteInfo chooseStationExtension( EuroGameInfo game, PlayerInfoMe player, CityInfo city, Set<RouteInfo> routes );
   
  /**
   * Returns tunneling option representing a way to pay for the additional requirements of tunnel (from turned-up cards).
   * <p>
   * Returning <code>null</code> means that this strategy chooses not to pay for the tunnel (and thus takes their initial cards back).
   * @param	game	<code>EuroGameInfo</code> representing the game being played
   * @param	player	<code>PlayerInfoMe</code> representing the player making the decision
   * @param payments <code>List</code> of <code>PaymentInfo</code>s representing all legal payments
   */
  PaymentInfo chooseAdditionalPayment( EuroGameInfo game, PlayerInfoMe player, RouteInfo route, Set<PaymentInfo> payments );
}