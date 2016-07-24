package com.underplex.tickay.game;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.PlayerInfo;
import com.underplex.tickay.info.RouteInfo;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.player.Player;

/**
 * Represents a route in Ticket to Ride.
 * <p>
 * A "route" here specifically means the gray or colored spaces between two cities that are directly connected. 
 * (It doesn't mean any connections between 3 or more cities.)
 * <p>
 * The "color" can be <code>GRAY</code> to mean wild payments, but the <code>color</code> field can never have a value of <code>LOCO</code> because
 * the field refers to the color of the entire route. Instead, the number of locos needed is trackes by the field <code>locos</code>.
 * <p>
 * The <code>length</code> field refers to the total number of trains needed to claim the route, with <code>locos</code> showing how many locos are
 * required.
 * <p>
 * In the TtR: Europe game, when routes have alternates, they are frequently of a different color than their parallel route. However, sometimes these
 * are both gray. In this case, even though they routes are the same in every way, they can both be built by different player and are thus distinct.
 * To ensure they return different hashCodes, the <code>hashMultiplier</code> field is set up so it will always default to 1 but be changed so it's opposite of the
 * alternate whenever the alternate is set.  
 * @author irvin_000
 *
 */
public class Route implements InfoSource<RouteInfo> {

	private int locos;
	private final int length;
	private final CityPair cities;
	private final TrainType color;
	private boolean tunnel;
	private Route alternate;
	private Player builder;
	private RouteInfo routeInfo;


	private int hashMultiplier; // used only internally to make sure alternate routes don't return same hashCode; should be 1 or -1.
	
	public Route(int length, City city1, City city2, TrainType color,
			boolean tunnel, int locos) {
		this.locos = locos;
		this.length = length;
		this.cities = new CityPair( city1, city2 );
		this.color = color;
		
		this.tunnel = tunnel;
		this.routeInfo = new RouteInfo(this);
		this.hashMultiplier = 1; // defaulted to 1, so must be changed whenever alternate is set
	}
	
	public boolean equals(Object obj){
		boolean rBool = true;
		if ( obj == null ){
			rBool = false;
		} else {
			if ( !(obj instanceof Route)){
				rBool = false;
			} else {
				Route other = (Route)obj;
				if ( this.alternate == other ) 	rBool = false;
				if ( !this.cities.equals( other.getCities()) )	rBool = false;
			}
		}
		return rBool;
	}

	public int hashCode(){
		return this.cities.hashCode() * this.hashMultiplier;
	}

	public String toShortLabelString(){
		return cities.getCity1().getName().toUpperCase() + "-" + cities.getCity2().getName().toUpperCase();
	}
	
	public String toString(){
		String rString = cities.getCity1().getName().toUpperCase() + "-" + cities.getCity2().getName().toUpperCase();
		rString += " " + this.length +" " + this.color;
		
		if ( this.locos == 1 ){
			rString += ", 1 LOCO";
		} else if ( this.locos >= 2 ){
			rString += ", " + this.locos + " LOCOS"; 
		}
		
		if ( this.tunnel )
			rString += ", tunnel";
		
		if ( this.isBuilt() ){
			rString += ", built by " + this.getBuilder().getPlayerType().toString();
		} else {
			rString += ", not built";
		}
		
		return rString;
	}
	
	public RouteInfo info() {
		return routeInfo;
	}

	public Route(int length, City city1, City city2, TrainType color) {
		this( length, city1, city2, color, false, 0 );
	}

	public Route(Route alternate, TrainType color) {
		this(alternate.getLength(), alternate.getCities().getCity1(), alternate.getCities()
				.getCity2(), color, alternate.isTunnel(), alternate.getLocos() );
		this.setAlternate( alternate );
	}

	/**
	 * Returns 1 or -1; paired routes should always have opposite signs.
	 */
	public int getHashMultiplier() {
		return hashMultiplier;
	}
	
	/**
	 * Returns number of loco trains required to build this.
	 * <p>
	 * This is just the number of loco symbols on the route on the board.
	 */
	public int getLocos() {
		return locos;
	}

	public int getLength() {
		return length;
	}

	public TrainType getColor() {
		return color;
	}

	public boolean isTunnel() {
		return tunnel;
	}

	public Route getAlternate() {
		return alternate;
	}

	public Player getBuilder() {
		return builder;
	}

	public void setLocos(int locos) {
		this.locos = locos;
	}

	public void setTunnel(boolean tunnel) {
		this.tunnel = tunnel;
	}

	public void setBuilder(Player builder) {
		this.builder = builder;
	}

	public CityPair getCities() {
		return cities;
	}

	public boolean isBuilt() {
		return (this.builder != null);
	}

	/**
	 * Returns <code>true</code> if the player has sufficient pieces and the track is not built (or it's alternate hasn't been built by the player).
	 * <p>
	 * Doesn't filter for whether player can afford to build this Route, only that he can legally do so.
	 */
	public boolean isBuildable( Player player, Game game ) {

		return isBuildable( player.info(), game );
	} // end isBuildable method
	
	/**
	 * Returns <code>true</code> if the player has sufficient pieces and the track is not built (or it's alternate hasn't been built by the player).
	 * <p>
	 * Doesn't filter for whether player can afford to build this Route, only that he can legally do so.
	 */
	public boolean isBuildable( PlayerInfo player, Game game ) {
		boolean rBool = true; // assume it's true and check every falsifying condition
		
		if ( this.isBuilt() )
			rBool = false;
		
		// if the player doesn't have the pieces to build this route
		if ( player.getPieceCount().count() < this.length )
			rBool = false;
		
		// if the route has an alternate route...
		if ( this.alternate != null ) { // it has a part of a pair, we need to check other conditions
			if ( game.getPlayers().size() <= 3 ) {
				// this route is unbuildable if the alternate is already built
				if ( this.alternate.isBuilt() )
					rBool = false;
			} else { // i.e. player > 3
				// if the alternate is built...
				if ( this.alternate.getBuilder() != null )
					// if the builder of the alternate is the player, this route can't be built by player
					if (this.alternate.getBuilder().info() == player)
						rBool = false;
			}
		} 

		return rBool;
	} // end isBuildable method
	

	/**
	 * Sets the alternate route with sideeffect of making this routes hashMultiplier have the opposite sign.
	 * <p>
	 * The route can't be set with constructor because often the alternate route
	 * doesn't exist yet when this is created.
	 */
	public void setAlternate(Route alternate) {
		this.alternate = alternate;
		this.hashMultiplier = (-1) * alternate.getHashMultiplier();
	}
	
	/**
	 * Returns number of points this route is worth if built.
	 */
	public int getPoints( Game game, Player player ){
		int rInt = 0;
		switch ( this.length ){
			
		case 1:
			rInt = 1;
			break;
			
		case 2:
			rInt = 2;
			break;
			
		case 3:
			rInt = 4;
			break;
	
		case 4:
			rInt = 7;
			break;
	
		case 5:
			rInt = 15;
			break;
		case 6:
			rInt = 15;
			break;
		case 7:
			rInt = 21;
			break;
		case 8:
			rInt = 21;
			break;
		}
		return rInt;
	}

} // end class
