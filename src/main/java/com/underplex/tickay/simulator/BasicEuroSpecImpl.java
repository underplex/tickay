package com.underplex.tickay.simulator;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.strategy.DefaultEuroStrategy;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tool.Specification;

/**
 * A basic implementation of the Tickay simulation specification.
 * @author irvin_000 *
 */
public class BasicEuroSpecImpl implements Specification<EuroStrategy> {

	private int iterations;
	private int players;
	private String expansions;
	
	public BasicEuroSpecImpl( int iterations, String expansions, int players ) {
		this.iterations = iterations;
		this.players = players;
		this.expansions = expansions;
	}

	public int iterations() {

		return this.iterations;
	}

	public int numberOfPlayers(int iteration) {

		return this.players;
	}

	public List<EuroStrategy> assignStrategies(int iteration) {
		List<EuroStrategy> strats = new ArrayList<>();
	
		for ( int i = 1; i <= this.players; i++)
			strats.add( new DefaultEuroStrategy() );
		
		return strats;
	}

	public String expansions() {
		
		return this.expansions;
	}
	
	

}
