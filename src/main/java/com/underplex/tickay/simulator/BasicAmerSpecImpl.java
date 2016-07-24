package com.underplex.tickay.simulator;

import java.util.ArrayList;
import java.util.List;

import com.underplex.tickay.strategy.AmericaStrategy;
import com.underplex.tickay.strategy.DefaultAmericaStrategy;
import com.underplex.tool.Specification;

/**
 * A basic implementation of the Tickay simulation specification.
 * @author Brandon Irvine
 */
public class BasicAmerSpecImpl implements Specification<AmericaStrategy> {

	private int iterations;
	private int players;
	private String expansions;
	
	public BasicAmerSpecImpl( int iterations, String expansions, int players ) {
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

	public List<AmericaStrategy> assignStrategies(int iteration) {
		List<AmericaStrategy> strats = new ArrayList<>();
	
		for ( int i = 1; i <= this.players; i++)
			strats.add( new DefaultAmericaStrategy() );
		
		return strats;
	}

	public String expansions() {
		
		return this.expansions;
	}
	
	

}
