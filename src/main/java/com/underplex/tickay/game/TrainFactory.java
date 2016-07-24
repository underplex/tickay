package com.underplex.tickay.game;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.jaxb.TrainType;

/**
 * Static utility class for producing train cards Ticket to Ride game.
 */
public class TrainFactory {

	private TrainFactory() {
		// don't instantiate
	}

	/**
	 * Returns <code>List</code> of <code>TrainType</code> representing all train cards to be used.
	 * <p>
	 * Not shuffled and not divided between a draw-deck and a discard-deck.
	 * @param expansions	<code>String</code> with version and expansions used
	 * @return	list of train cards
	 */
	public static List<TrainType> makeDeck(String expansions) {
		List<TrainType> rList = new ArrayList<>();

		if ( expansions.toUpperCase().contains( "EUROPE" ) 
				|| expansions.toUpperCase().contains( "AMERICA" ) ) {
			
			for (TrainType t : TrainType.values()) {

				if (t != TrainType.LOCO && t != TrainType.GRAY)
					// add 12 cards to deck of any non-loco, non-gray
					rList.addAll( makeBatch( t, 12 ) ); 

			} // end for
			
			rList.addAll( makeBatch( TrainType.LOCO, 14 ) );

		} // end if (expansion is Europe)
		return rList;
	} // end class

	public static List<TrainType> makeBatch( TrainType t, int number ) {
		List<TrainType> rList = new ArrayList<>();

		for (int i = 1; i <= number; i++)
			rList.add( t );
		
		return rList;
	}

} // end class
