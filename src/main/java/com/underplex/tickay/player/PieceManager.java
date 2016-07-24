package com.underplex.tickay.player;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.PieceManagerInfo;

public class PieceManager implements InfoSource<PieceManagerInfo>{

	private Player player;
	private int pieces;
	private PieceManagerInfo info;

	public PieceManager(Player player) {
		this.player = player;
		this.pieces = 45;
		this.info = new PieceManagerInfo(this);
	}

	public PieceManagerInfo info(){
		return this.info;
	}
	
	/**
	 * Number of pieces.
	 */
	public int count() {
		return this.pieces;
	}

	public boolean endsGame() {
		return (this.pieces <= 2);
	}

	/**
	 * Places a <code>length</code> number of pieces on board by taking them out of the supply of pieces.
	 * @param length
	 */
	public void place(int length){
		this.pieces -= length;
	}
	
	public Player getPlayer(){
		return this.player;
	}

}
