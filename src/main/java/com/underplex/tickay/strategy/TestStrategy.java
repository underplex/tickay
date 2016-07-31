package com.underplex.tickay.strategy;

import java.util.List;
import java.util.Set;

import com.underplex.tickay.info.CityInfo;
import com.underplex.tickay.info.EuroGameInfo;
import com.underplex.tickay.info.GameInfo;
import com.underplex.tickay.info.PaymentInfo;
import com.underplex.tickay.info.PlayInfo;
import com.underplex.tickay.info.PlayerInfoMe;
import com.underplex.tickay.info.RouteInfo;
import com.underplex.tickay.info.SecondTakePlayInfo;
import com.underplex.tickay.info.TicketChoicePlayInfo;
import com.underplex.tickay.info.TicketInfo;

/**
 * A skeletal implementation of <code>Strategy</code>.
 * <p>
 * This strategy makes no attempt to actually make legal choices in the game and probably best serves simply as a place-holder when mocking player for tests.
 */
public class TestStrategy implements EuroStrategy {

	@Override
	public PlayInfo chooseRegularPlay(GameInfo game, PlayerInfoMe player,
			Set<PlayInfo> plays) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TicketInfo> discardFirstTickets(GameInfo game,
			PlayerInfoMe player, Set<TicketInfo> options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecondTakePlayInfo chooseSecondTake(GameInfo game,
			PlayerInfoMe player, Set<SecondTakePlayInfo> options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TicketChoicePlayInfo chooseReturnTickets(GameInfo game,
			PlayerInfoMe player, List<TicketChoicePlayInfo> options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RouteInfo chooseStationExtension(EuroGameInfo game,
			PlayerInfoMe player, CityInfo city, Set<RouteInfo> routes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentInfo chooseAdditionalPayment(EuroGameInfo game,
			PlayerInfoMe player, RouteInfo route, Set<PaymentInfo> payments) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getIdentifier(){
		return this.getClass().getSimpleName();
	}



}
