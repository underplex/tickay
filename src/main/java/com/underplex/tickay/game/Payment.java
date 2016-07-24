package com.underplex.tickay.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.PaymentInfo;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.player.Player;

/**
 * Represents a specific way to pay a cost in train cards to be paid by <code>player</code>.
 * <p>
 * To pay for a Route, for example, there might be several ways to pay with cards in a given hand of train cards, and this will represent on of those ways to pay, using some or all of the available locos in the hand to pay costs.
 * <p>
 * Note that payments are made in terms of "locos" not "gray" items, since there are no "gray" cards.
 * <p>
 * The trainType is the main type associated with the payment. This can't have the value TrainType.GRAY because that's not a train color.
 * <p>
 * If trainType is TrainType.LOCO, that means that this entire payment is paid with locos (and regulars should equal 0).
 * <p>
 * Otherwise, the trainType indicates a regular color and regulars indicates the number of that color (and locos indicates any locos added).
 * @author irvin_000
 *
 */
public class Payment implements InfoSource<PaymentInfo>{

	private TrainType trainType;
	private int regulars;
	private int locos;
	private final PaymentInfo info;
	private final Player player;

	public Payment( TrainType trainType, int regulars, int locos, Player player ) {
		this.player = player;
		this.trainType = trainType;
		this.regulars = regulars;
		this.locos = locos;
		this.info = new PaymentInfo( this );
	}
	
	public PaymentInfo info(){
		return this.info;
	}
	
	public Payment(int locos, Player player ) {
		this( TrainType.LOCO, 0, locos, player );
	}

	public boolean equals(Object obj){
		boolean rBool = true;
		if ( !(obj instanceof Payment) ){
			rBool = false;
		} else {
			Payment other = (Payment)obj;
			// for every train type, if these have the same number, they are the same payment
			for ( TrainType tt : TrainType.values() )
				if ( Collections.frequency(other.list(), tt) != Collections.frequency(this.list(), tt)) 
					rBool = false;
		}
		return rBool;
	}

	public int hashCode(){
		int rInt = 1;
		for ( TrainType tt : this.list() )
			rInt *= tt.hashCode();
		return rInt;		
	}
	
	public String toString(){
		String rString = "";
		if ( regulars == 0 ){
			rString += "no color trains, ";
		} else {
			rString += this.regulars + " " + this.trainType + ", "; 
		}
		rString += this.locos + " LOCO";
		return rString;
	}
	
	/**
	 * The total number of cards that this uses to pay.
	 */
	public int size() {
		return regulars + locos;
	}

	public Payment copy() {
		return new Payment( this.trainType, this.regulars, this.locos, this.player );
	}

	/**
	 * Returns this in list form.
	 */
	public List<TrainType> list() {
		List<TrainType> rList = new ArrayList<>();
		for (int i = 1; i <= regulars; i++)
			rList.add( this.trainType );
		for (int j = 1; j <= locos; j++)
			rList.add( TrainType.LOCO );
		return rList;
	}

	/**
	 * The main type of train that is used to pay this.
	 */
	public TrainType getTrainType() {
		return trainType;
	}

	/**
	 * The number of the <code>trainType</code> cards that will be used.
	 */
	public int getRegulars() {
		return regulars;
	}

	public int getLocos() {
		return locos;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setTrainType(TrainType trainType) {
		this.trainType = trainType;
	}

	public void setRegulars(int regulars) {
		this.regulars = regulars;
	}

	public void setLocos(int locos) {
		this.locos = locos;
	}
}
