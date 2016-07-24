package com.underplex.tickay.simulator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.tickay.game.AmericaGame;
import com.underplex.tickay.jaxb.AmericaSimulationLog;
import com.underplex.tickay.jaxb.SpecErrorEntry;
import com.underplex.tickay.jaxb.SpecErrorType;
import com.underplex.tickay.strategy.AmericaStrategy;
import com.underplex.tickay.strategy.DefaultAmericaStrategy;
import com.underplex.tool.Specification;


/**
 * Simulates game of Ticket to Ride Americape.
 *  
 * @author Brandon Irvine
 *
 */
public class AmericaSimulator<T extends AmericaStrategy> extends TickaySimulator<T>{

	public AmericaSimulator(Specification<T> spec){
		super(spec);
		this.expansionsRequired.add("AMERICA");
	}
	
	public AmericaSimulationLog run() {
		
		Set<SpecErrorEntry> specErrors = new HashSet<SpecErrorEntry>();
		
		SpecErrorEntry its = this.validateIterations();
		if ( its != null ) specErrors.add( its );
		specErrors.addAll( this.validateExpansions() );

		// now, having checked expansions and iterations, produce the actual log
		AmericaSimulationLog log = this.doGames();

		// attach log-level metadata to the log object (the game should already be there)
		log.setDateRun( this.makeDateStamp() );
		log.setSpecClassName( this.spec.getClass().getSimpleName() );
		log.setExpansions( expansions );
		log.getError().addAll( specErrors );
		
		return log;
	
	} // end method
	
	/**
	 * Returns <code>SimulationLog</code> for basic Americape game.
	 * <p>
	 * Generates errors that are added to returned log if strategies assigned are not <code>AmericaStrategy</code> or if the 
	 * number of player for a given iteration is < 2 or > 5.
	 */
	public AmericaSimulationLog doGames(){
		
		AmericaSimulationLog rLog = new AmericaSimulationLog();
		int totalPlayers = 0;
		List<AmericaStrategy> strats; // wildcard fixes compilere error created by spec.assignStrategies(i) below
		SpecErrorEntry specError; // whatever error is currently occurring
		List<SpecErrorEntry> specErrors = rLog.getError(); // the log-level list of errors created by the specification
		String specName = this.spec.getClass().getSimpleName();
		
		for ( int i = 1; i <= this.iterations; i++ ) {
			
			strats = new ArrayList<>();
			
			for (T strat : spec.assignStrategies( i ) ){
				if ( strat instanceof AmericaStrategy ){
					strats.add((AmericaStrategy)strat);
				} else {
					specError = new SpecErrorEntry();
					specError.setSpecClass(specName);
					specError.setSpecError(SpecErrorType.STRATEGY_ASSIGNMENT);
					specErrors.add(specError);
				}
			}

			totalPlayers = strats.size();
			if ( totalPlayers < 2 || totalPlayers > 5 ){

				specError = new SpecErrorEntry();
				specError.setSpecClass(specName);
				specError.setSpecError(SpecErrorType.NUMBER_OF_PLAYERS);
				specErrors.add(specError);
				
				while (strats.size() < this.numPlayersDefault) {
					strats.add( new DefaultAmericaStrategy() );
				}
			}
		
			AmericaGame euroGame = new AmericaGame( strats, expansions );
			
			rLog.getAmericaGameEntry().add( euroGame.run() );
		}
		return rLog;
	}
	
}
