package com.underplex.tickay.player;
import com.underplex.infowrap.InfoSource;
import com.underplex.infowrap.MyInfoSource;
import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.info.EuroPlayerInfo;
import com.underplex.tickay.info.EuroPlayerInfoMe;
import com.underplex.tickay.jaxb.PlayerType;
import com.underplex.tickay.strategy.EuroStrategy;


public class EuroPlayer extends Player implements InfoSource<EuroPlayerInfo>, MyInfoSource<EuroPlayerInfoMe>{

	// defining aspects of a player  
	protected final EuroGame game;
	private final EuroPlayerInfo info;
	private final EuroPlayerInfoMe myInfo;
	protected final EuroStrategy strategy;
	protected final StationManager stations;
	
	public EuroPlayer( EuroStrategy strategy, int seat, EuroGame game){

		super(seat, game);
		this.stations = new StationManager( this );
		this.game = game;
		this.strategy = strategy;
		
		this.myInfo = new EuroPlayerInfoMe( this );
		this.info = new EuroPlayerInfo( this );
	}
	
	public boolean equals(Object obj){
		boolean rBool = true;
		if ( obj == null ){
			rBool = false;
		} else if ( obj instanceof EuroPlayer ){
			// we can cast and then check that the strategies are the same, which they need to be anyway
			EuroPlayer player = (EuroPlayer)obj;
			
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
	
	public EuroGame getGame() {
		return game;
	}

	public EuroPlayerInfo info() {
		return this.info;
	}
	
	public EuroPlayerInfoMe myInfo(){
		return this.myInfo;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public EuroStrategy getStrategy() {
		return strategy;
	}

	public NetworkManager getNetworks() {
		return networks;
	}

	public RouteManager getRoutes() {
		return routes;
	}

	public StationManager getStations() {
		return stations;
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
