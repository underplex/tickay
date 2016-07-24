package com.underplex.tickay.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.underplex.infowrap.InfoSource;
import com.underplex.infowrap.MyInfoSource;
import com.underplex.tickay.game.Payment;
import com.underplex.tickay.info.TrainHandManagerInfo;
import com.underplex.tickay.info.TrainHandManagerInfoMe;
import com.underplex.tickay.jaxb.TrainType;

@SuppressWarnings("serial")
public class TrainHandManager extends ArrayList<TrainType> implements InfoSource<TrainHandManagerInfo>, MyInfoSource<TrainHandManagerInfoMe>{
	
	private Player player;
	private TrainHandManagerInfo info; 
	private final TrainHandManagerInfoMe myInfo;
	
	public TrainHandManager( Player player ) {
		this.player = player;
		this.info = new TrainHandManagerInfo( this );
		this.myInfo = new TrainHandManagerInfoMe( this );
	}
	
	public TrainHandManagerInfo info(){
		return this.info;
	}

	public TrainHandManagerInfoMe myInfo(){
		return this.myInfo;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	 * The player pays <code>payment</code> and the game's discard has the paid cards added.
	 * @param payment
	 */
	public void pays( Payment payment ){
		for ( TrainType tt : payment.list() ){
			this.remove(tt);
			this.player.getGame().getTrains().toDiscard(tt);
		}
	}
	
	/**
	 * Returns the number of <code>type</code> train cards in hand.
	 */
	public int count( TrainType type ){
		return Collections.frequency(this, type);
	}
	
	/**
	 * Return a copy of this hand manager that can be changed without changing the actual game state.
	 */
	public TrainHandManager copy(){
		TrainHandManager rRef = new TrainHandManager( this.player );
		rRef.addAll( this );
		
		return rRef;
	}
	
	/**
	 * Returns defensive copy of the cards in this hand as a list.
	 */
	public List<TrainType> list(){
		return new ArrayList<TrainType>( this );
	}
	
}
