package com.underplex.tickay.game;

import java.util.HashSet;
import java.util.Set;

import com.underplex.infowrap.InfoWrapper;
import com.underplex.tickay.info.TicketInfo;

public class ChoiceValidator {

	private ChoiceValidator() {
		// don't instantiate
	}

	/**
	 * Return true if and only if <code>decisions</code> represents a completely legal set of cards to discard on your first turn.
	 * 
	 * @param options		set of options the actual cards of which 0 to 2 may be chosen for discard
	 * @param decisions		set of decisions representing cards a strategy is attempting to discard
	 * @return				true if discard decisions are legal
	 */
	public static boolean firstDiscard( Set<Ticket> options, Set<TicketInfo> decisions ) {
		
		boolean rBoolean = true;
		Ticket unwrapped = null;
		
		Set<Ticket> unwrappedSet = new HashSet<>();

		if ( decisions == null ) {
			rBoolean = false;
		} else {
			for ( TicketInfo info : decisions ){
				unwrapped = InfoWrapper.unwrap( options, info );
				if ( unwrapped == null && rBoolean ){ // if one of the cards in decisions isn't actually an option
					rBoolean = false;
				} else {
					unwrappedSet.add( unwrapped );	// if it's okay, add it to the set of cards to remove
				}
			}
		}
		
		if ( unwrappedSet.size() > 2 ){ // you can't discard more than 2 cards
			rBoolean = false;
		}
		
		return rBoolean;
	}

}
