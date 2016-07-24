package com.underplex.tickay.info;

import com.underplex.tickay.jaxb.TrainType;

/**
 * Immutable wrapper for a legal option for a player choosing a second card when
 * doing the taking trains action.
 * <p>
 * The trainType field represents a type of train to take from the face up cards
 * available. If null, this option represents taking a blind draw from the deck.
 * <p>
 * Only instantiate this class when it will represent a legal option.
 * 
 * @author irvin_000
 *
 */
public class SecondTakePlayInfo {

	private final TrainType trainType;

	public SecondTakePlayInfo(TrainType trainType) {
		this.trainType = trainType;
	}

	/**
	 * Returns type of train that this play would take from the face-ups, or <code>null</code> if it would be a draw from the deck.
	 */
	public TrainType getTrainType() {
		return this.trainType;
	}

	public String toString() {
		String card = (this.trainType == null ? "deck draw" : "face-up "
				+ this.trainType.toString());
		return ("PLAY CHOICE: take trains, second " + card);
	}
}
