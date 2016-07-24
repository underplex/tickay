package com.underplex.tickay.player;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.info.PlayerInfo;
import com.underplex.tickay.info.PlayerInfoMe;
import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.strategy.Strategy;
import com.underplex.tickay.util.TickayFinder;

public abstract class Player{

	// defining aspects of a player  
	protected final PlayerType playerType;
	protected final Game game;
	// managers to take care of all player aspects
	protected final NetworkManager networks;
	protected final RouteManager routes;
	protected final PieceManager pieces;
	protected final PointManager points;     	
	protected final TrainHandManager trains;
	protected final TicketHandManager tickets;
	
	protected Player( int seat, Game game ){
		
		// use static method to assign a type to EuroPlayer 
		// the JAXB class ought to do this but it's better not to customize JAXB classes
		this.playerType = TickayFinder.typeFromInt( seat ); 
		this.pieces = new PieceManager( this );
		this.points = new PointManager( this );

		this.routes = new RouteManager(this);
		this.networks = new NetworkManager(this);

		this.trains = new TrainHandManager( this );
		this.tickets = new TicketHandManager( this );
		this.game = game;
	}

	public boolean equals(Object obj){
		boolean rBool = true;
		if ( obj == null ){
			rBool = false;
		} else if ( obj instanceof Player ){
			// we can cast and then check that the strategies are the same, which they need to be anyway
			Player player = (Player)obj;
			
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

	public PlayerType getPlayerType() {
		return playerType;
	}

	public Game getGame() {
		return game;
	}

	public abstract PlayerInfo info();
	
	public abstract PlayerInfoMe myInfo();
	
	public abstract Strategy getStrategy();	
		
	public NetworkManager getNetworks() {
		return networks;
	}

	public RouteManager getRoutes() {
		return routes;
	}

	public PieceManager getPieces() {
		return pieces;
	}

	public PointManager getPoints() {
		return points;
	}

	public TrainHandManager getTrains() {
		return trains;
	}

	public TicketHandManager getTickets() {
		return tickets;
	}

}
