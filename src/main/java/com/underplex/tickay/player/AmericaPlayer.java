package com.underplex.tickay.player;
import com.underplex.infowrap.InfoSource;
import com.underplex.infowrap.MyInfoSource;
import com.underplex.tickay.game.AmericaGame;
import com.underplex.tickay.info.AmericaPlayerInfo;
import com.underplex.tickay.info.AmericaPlayerInfoMe;
import com.underplex.tickay.strategy.AmericaStrategy;


public class AmericaPlayer extends Player implements InfoSource<AmericaPlayerInfo>, MyInfoSource<AmericaPlayerInfoMe>{

	// defining aspects of a player  
	protected final AmericaGame game;
	private final AmericaPlayerInfo info;
	private final AmericaPlayerInfoMe myInfo;
	protected final AmericaStrategy strategy;
	
	public AmericaPlayer( AmericaStrategy strategy, int seat, AmericaGame game){

		super(seat, game);
		this.game = game;
		this.strategy = strategy;
		
		this.myInfo = new AmericaPlayerInfoMe( this );
		this.info = new AmericaPlayerInfo( this );
	}
	
	public boolean equals(Object obj){
		boolean rBool = true;
		if ( obj == null ){
			rBool = false;
		} else if ( obj instanceof AmericaPlayer ){
			// we can cast and then check that the strategies are the same, which they need to be anyway
			AmericaPlayer player = (AmericaPlayer)obj;
			
			if ( !player.getStrategy().equals(this.getStrategy()) )
				rBool = false;
				
			if ( player.getPlayerType() != this.getPlayerType() )
				rBool = false;
		} else {
			rBool = false;
		}
		
		return rBool;
	}
	
	public int hashCode(){
		return this.playerType.hashCode();
	}
	
	public AmericaGame getGame() {
		return game;
	}

	public AmericaPlayerInfo info() {
		return this.info;
	}
	
	public AmericaPlayerInfoMe myInfo(){
		return this.myInfo;
	}

	public AmericaStrategy getStrategy() {
		return strategy;
	}
}
