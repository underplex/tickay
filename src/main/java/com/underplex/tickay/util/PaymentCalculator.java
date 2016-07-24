package com.underplex.tickay.util;

import java.util.HashSet;
import java.util.Set;

import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.game.Payment;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.player.EuroPlayer;
import com.underplex.tickay.player.TrainHandManager;

/**
 * Utility class that with static methods for calculating legal payments.
 */
public class PaymentCalculator {

	private PaymentCalculator() {
		// don't instantiate
	}
	
	/**
	 * Returns set representing all the ways to pay for a number of cards (<code>cost</code) given <code>hand</code> for a
	 * cost of trainType.
	 */
	public static Set<Payment> findPayments( int cost, TrainType trainType, TrainHandManager hand) {

		Set<Payment> rSet = new HashSet<>();
		Set<Payment> incomplete = findLocoPartials( cost, hand, 0 );
		
		for ( Payment payment : incomplete ) {

			int missing = cost - payment.size();
			if ( missing == 0 ) {
				rSet.add(payment); // cost is already paid off with locos
			} else if ( ( hand.count( trainType ) >= missing ) ) {
				payment.setTrainType( trainType ); // overwriting the loco type as the trainType of the payment
				payment.setRegulars( missing );
				rSet.add(payment);
			} // end if-else
		} // end for
		
		return rSet;
	}// end method

	/**
	 * Returns set representing all the ways to pay for a Cost given Hand for a
	 * "wild" color cost (a gray route or station cost, for example).
	 */
	public static Set<Payment> findWildPayments( int cost, TrainHandManager hand ) {
		return findWildPaymentsWithLocos( cost, hand, 0 );
	} // end method
	
	/**
	 * Returns set representing all the ways to pay for a Cost given Hand for a
	 * "wild" color cost plus a number of required locos (some of these are "ferries").
	 *
	 * @param locos
	 *            number of locos required
	 */
	public static Set<Payment> findWildPaymentsWithLocos( int cost, TrainHandManager hand, int locos ) {
		Set<Payment> rSet = new HashSet<>();
		Set<Payment> incomplete;
		
		// note that if there aren't enough locos in hand, the rSet will be returned empty
		if ( hand.count( TrainType.LOCO ) >= locos ) {
			incomplete = findLocoPartials( cost, hand, locos );

			Payment prime;

			for ( Payment payment : incomplete ) {

				int missing = cost - payment.size();
				if ( missing == 0 ) {
					rSet.add( payment ); // this is already paid off with locos
				} else {
					// can we pay missing with some other color trains?
					// if so, do that
					for ( TrainType trainType : TrainType.values() ) {
						if ( trainType != TrainType.GRAY && trainType != TrainType.LOCO ){
							if ( hand.count( trainType ) >= missing ) {
								prime = payment.copy();
								prime.setTrainType( trainType ); //overwrite the trainType for regulars
								prime.setRegulars( missing );
								rSet.add( prime );
							}
						}
					} // end for traintype
				} // end if-else
			} // end for payment
		} // end if hand >= locos needed
		return rSet;
	} // end method

	/**
	 * Returns Payments that can be made by player to pay additional number of cards of the same color as base payment. 
	 */
	public static Set<Payment> findTunnelPayments( EuroGame euroGame, EuroPlayer euroPlayer, Payment base, int additional){
		// make afterPayment as a hypothetical hand given the payment
		TrainHandManager afterPayment = euroPlayer.getTrains().copy();
		// now, theoretical hand would have the payment already taken out (but cards shouldn't go to this game's discard, so we don't use normal method)
		for ( TrainType tt : base.list() ){
			afterPayment.remove(tt);
		}
		
		// find ways to pay for the new additional cost
		return PaymentCalculator.findPayments( additional, base.getTrainType(), afterPayment );
	}
	
	/**
	 * Returns all arrangements of partial payments for <code>cost</code> using locos (with minimum of <code>locos</code> used).
	 */
	private static Set<Payment> findLocoPartials( int cost, TrainHandManager hand, int locos ){
		Set<Payment> rSet = new HashSet<>();
		
		int locoLimit = Math.min( hand.count( TrainType.LOCO ), cost );

		for ( int i = locos; i <= locoLimit; i++ )
			// create payment representing each number of locos
			rSet.add( new Payment( TrainType.LOCO, 0, i, hand.getPlayer() ) );
		
		return rSet;		
	}
}
