package com.underplex.tickay.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.underplex.tickay.game.LengthType;
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
import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.util.PlaySorter;
import com.underplex.tool.Picker;

public class DefaultEuroStrategy implements EuroStrategy {

	public Set<TicketInfo> discardFirstTickets( GameInfo game, PlayerInfoMe player, Set<TicketInfo> options ) {
		Set<TicketInfo> rSet = new HashSet<>();
		for ( TicketInfo ti : options )
			if ( rSet.size() < 2 && ti.getLengthType() == LengthType.SHORT )
				rSet.add(ti);
		
		return rSet;
	}
	
	public PlayInfo chooseRegularPlay( GameInfo game, PlayerInfoMe player, Set<PlayInfo> plays ) {
		
		Map<PlayType, Set<PlayInfo>> allPlays = PlaySorter.sortByType( plays );
		Set<PlayInfo> claims = allPlays.get( PlayType.CLAIM_ROUTE);
		Set<PlayInfo> trains = allPlays.get( PlayType.TAKE_TRAINS);
		
		// choose a random play at first
		PlayInfo rPlay;
		
		if ( player.getMyTrainHand().size() > 10 && claims.size() > 0 ){
			rPlay = Picker.selectRandom( claims );
		} else if ( trains.size() > 0) {
			rPlay = Picker.selectRandom( trains );
		} else {
			rPlay = Picker.selectRandom( plays );
		}
		
		return rPlay;
	}

	public SecondTakePlayInfo chooseSecondTake(GameInfo game, PlayerInfoMe player, Set<SecondTakePlayInfo> options) {
		
		return Picker.selectRandom( options );
	}

	public RouteInfo chooseStationExtension(EuroGameInfo game, PlayerInfoMe player, CityInfo city, Set<RouteInfo> routes) {
		
		return Picker.selectRandom( routes );
	}

	public TicketChoicePlayInfo chooseReturnTickets(GameInfo game, PlayerInfoMe player, List<TicketChoicePlayInfo> optionsInfo) {
		return Picker.selectRandom( optionsInfo );
	}

	public PaymentInfo chooseAdditionalPayment(EuroGameInfo game,
			PlayerInfoMe player, RouteInfo route,
			Set<PaymentInfo> paymentOptions) {
		return Picker.selectRandom( paymentOptions );
	}
	
	public String getIdentifier(){
		return this.getClass().getSimpleName();
	}

}
