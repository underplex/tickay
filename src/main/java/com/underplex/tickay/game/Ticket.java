package com.underplex.tickay.game;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.TicketInfo;


public class Ticket implements InfoSource<TicketInfo> {

	private final int points;
	private final CityPair cityPair;
	private final LengthType lengthType;
	private final TicketInfo info;
	private final String cityString;
	
	public Ticket( City city1, City city2, int points ) {
		
		this.cityPair = new CityPair(city1, city2);
		this.points = points;
		if ( points >= 20 ) {
			this.lengthType = LengthType.LONG;
		} else {
			this.lengthType = LengthType.SHORT;
		}
		this.cityString = city1.getName() + "=" + city2.getName();
		this.info = new TicketInfo(this);
	}

	public TicketInfo info(){
		return info;
	}
	
	public String toString(){
		return cityPair.getCity1().getName() + "=" + cityPair.getCity2().getName();
	}
	
	/**
	 * Returns true if <code>Object</code> is a <code>Ticket</code> with the same end cities as this Ticket.
	 * <p>
	 * Returns <code>false</code> if Object is null.
	 */
	public boolean equals(Object obj){
		if ( obj instanceof Ticket ){ //instanceof has its own nullcheck
			Ticket other = (Ticket)obj;
			return ( this.cityPair.equals( other ) );
		}
		
		return false;
		
	}

	public int getPoints() {
		return points;
	}

	public CityPair getCityPair() {
		return cityPair;
	}

	public LengthType getLengthType() {
		return lengthType;
	}
}
