package com.underplex.tickay.info;

import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.play.StationPlay;

/**
 * Immutable wrapper for the option to build a station as a play.
 * @author Brandon Irvine
 *
 */
public class StationPlayInfo implements PlayInfo {

	private StationPlay source;

	/**
	 * Constructor used by simulator.
	 */
	public StationPlayInfo(StationPlay source) {
		this.source = source;
	}

	/**
	 * Returns city where the station would be built with this play.
	 */
	public CityInfo getCity() {
		return this.source.getCity().info();
	}

	/**
	 * Returns the payment that would be used to build the station for this play.
	 */
	public PaymentInfo getPayment() {
		return this.source.getPayment().info();
	}
	
	public PlayType getPlayType() {
		return PlayType.BUILD_STATION;
	}

}
