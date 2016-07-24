package com.underplex.tickay.play;

import com.underplex.tickay.game.City;
import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.game.Payment;
import com.underplex.tickay.info.StationPlayInfo;
import com.underplex.tickay.jaxb.BuildStationEntry;
import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.player.EuroPlayer;
import com.underplex.tickay.player.Player;

/**
 * Represents option to build a station at a particular city using a particular payment.
 * <p>
 * Only instantiate this class in a state that it represents a legal play for the player in the current game state.
 * @author irvin_000
 *
 */
public class StationPlay implements Play {

	private City city;
	private Payment payment;
	private StationPlayInfo info;

	public StationPlay( City city, Payment payment ) {
		this.city = city;
		this.payment = payment;
		this.info = new StationPlayInfo(this);
	}
	
	/**
	 * Returns entry representing the building of a station.
	 * <p>
	 * If <code>game</code> is not of type <code>EuroGame</code> and <code>player</code> isn't of type <code>EuroPlayer</code> this has no defined behavior and this
	 * method shouldn't be called unless it's changed for use with other expansions. 
	 */
	public BuildStationEntry resolve( Game game, Player player ) {

		EuroGame euroGame = null;
		EuroPlayer euroPlayer = null;
		
		if ( game instanceof EuroGame) euroGame = (EuroGame)game;
		if ( player instanceof EuroPlayer) euroPlayer = (EuroPlayer)player;
		
		euroPlayer.getTrains().pays( payment );
		euroPlayer.getStations().built().add( city );
		city.placeStation( euroPlayer );

		BuildStationEntry entry = new BuildStationEntry();
		entry.setPlayer( euroPlayer.getPlayerType() );
		entry.setStationLocation( city.getName() );
		entry.setStationNumber( euroPlayer.getStations().built().size() );
		entry.getSpentForStation().addAll( payment.list() );

		return entry;
	}

	public PlayType getPlayType() {
		return PlayType.BUILD_STATION;
	}
	
	public String toString(){
		return "PLAY: station at " + this.city.getName() + " paid with " + this.payment.toString();
	}

	public City getCity() {
		return city;
	}

	public Payment getPayment() {
		return payment;
	}

	public StationPlayInfo info(){
		return info;
	}
}
