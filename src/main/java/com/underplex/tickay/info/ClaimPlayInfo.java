package com.underplex.tickay.info;

import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.play.ClaimPlay;

/**
 * Immutable wrapper for class representing a play that claims a route for a player.
 * @author Brandon Irvine
 *
 */
public class ClaimPlayInfo implements PlayInfo {

	private ClaimPlay source;

	/**
	 * Constructor used by simulator.
	 * @param source
	 */
	public ClaimPlayInfo(ClaimPlay source) {
		this.source = source;
	}
	
	public String toString() {
		return source.toString();
	}

	/**
	 * Returns route this would build.
	 */
	public RouteInfo getRoute() {
		return source.getRoute().info();
	}

	/**
	 * Returns main payment this would use (not including any additional required by tunnel).
	 */
	public PaymentInfo getPayment() {
		return source.getPayment().info();
	}

	public PlayType getPlayType() {
		return PlayType.CLAIM_ROUTE;
	}

}
