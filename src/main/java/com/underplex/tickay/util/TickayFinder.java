package com.underplex.tickay.util;

import com.underplex.tickay.jaxb.PlayerType;

/**
 * Utility class for finding/matching values in Ticket to Ride to appropriate references/values.
 * @author Brandon Irvine
 *
 */
public class TickayFinder {
	
	private TickayFinder(){
		// don't instantiate
	}

	/**
	 * Returns the player type associated with <code>seat</code>.
	 */
	public static PlayerType typeFromInt( int seat ){
		
		PlayerType rType = PlayerType.APU;
		switch (seat) {		
		case 1:
			rType = PlayerType.APU; 
			break;
		case 2:
			rType = PlayerType.BARNEY; 
			break;
		case 3:
			rType = PlayerType.CARL; 
			break;
		case 4:
			rType = PlayerType.DUFFMAN; 
			break;
		case 5:
			rType = PlayerType.EDNA; 
			break;
		}
		
		return rType;
		
	}

}
