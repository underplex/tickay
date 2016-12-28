package com.underplex.tickay.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.underplex.tickay.strategy.ABFactory;
import com.underplex.tickay.strategy.AmericaStrategy;
import com.underplex.tickay.strategy.DefaultAmericaStrategy;
import com.underplex.tool.Specification;

/**
 * A specification for AB testing two strategies.
 * 
 * @author Brandon Irvine
 */
public class ABSpecImpl implements Specification<AmericaStrategy> {

	private int iterations;
	private int players;
	private String expansions;
	private ABFactory<AmericaStrategy> factory;
	
	public ABSpecImpl( int iterations, String expansions, int players, ABFactory<AmericaStrategy> factory ) {
		this.iterations = iterations;
		this.players = players;
		this.expansions = "america";
		this.factory = factory;
	}

	public int iterations() {

		return this.iterations;
	}

	public int numberOfPlayers(int iteration) {

		return this.players;
	}

	public List<AmericaStrategy> assignStrategies(int iteration) {
		List<AmericaStrategy> strats = new ArrayList<>();
		Random random = new Random();
		
		
		for ( int i = 1; i <= this.players; i++){
			AmericaStrategy nextStrat;
			if (random.nextBoolean()){
				nextStrat = factory.makeA();
			} else {
				nextStrat = factory.makeB();
			}
			strats.add( new DefaultAmericaStrategy() );
		
		}
		
		return strats;
	}

	public String expansions() {
		
		return this.expansions;
	}
	
	

}
