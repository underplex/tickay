package com.underplex.tickay.info;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.game.Payment;
import com.underplex.tickay.jaxb.TrainType;

/**
 * Represents a payment to be made.
 * @author Brandon Irvine
 *
 */
public class PaymentInfo {
	
	private Payment source;

	/**
	 * Constructor used by the simulator.
	 * @param source
	 */
	public PaymentInfo(Payment source) {
		this.source = source;
	}

	/**
	 * Returns total number of train cards this payment includes.
	 */
	public int size() {
		return source.size();
	}

	/**
	 * Returns <code>List</code> with each element representing one of the cards to be paid.
	 * <p>
	 * The returned <code>List</code> is a defensive copy of the actual list, so changing it won't have an effect on the actual payment.
	 */
	public List<TrainType> list() {
		return new ArrayList<TrainType>( source.list() );
	}

	/**
	 * Returns the <code>TrainType</code> of the non-<code>LOCO</code> train cards to be paid.
	 */
	public TrainType getTrainType() {
		return source.getTrainType();
	}

	/**
	 * Returns the number of non-<code>LOCO</code> train cards to be paid.
	 */
	public int getRegulars() {
		return source.getRegulars();
	}
	/**
	 * Returns the number of <code>LOCO</code> train cards to be paid.
	 */
	public int getLocos() {
		return source.getLocos();
	}
	/**
	 * Returns the player to pay this payment.
	 */
	public PlayerInfo getPlayer() {
		return source.getPlayer().info();
	}
	
	
}
