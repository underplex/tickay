package com.underplex.tickay.info;

import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.play.TakeTrainsPlay;

/**
 * Immutable wrapper for play that takes train cards.
 * 
 * @author Brandon Irvine
 *
 */
public class TakeTrainsPlayInfo implements PlayInfo {

	private TakeTrainsPlay source;

	public TakeTrainsPlayInfo(TakeTrainsPlay source) {
		this.source = source;
	}

	public PlayType getPlayType() {
		return PlayType.TAKE_TRAINS;
	}

	/**
	 * Returns train card that would be taken from face-ups or
	 * <code>null</code> if this would be a blind draw from deck.
	 */
	public TrainType getTrainType() {
		return source.getTrainType();
	}

}
