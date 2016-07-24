package com.underplex.tickay.simulator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.jaxb.EuroSimulationLog;
import com.underplex.tickay.jaxb.SpecErrorEntry;
import com.underplex.tickay.jaxb.SpecErrorType;
import com.underplex.tickay.strategy.DefaultEuroStrategy;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tool.Specification;


/**
 * Simulates game of Ticket to Ride Europe.
 *  
 * @author Brandon Irvine
 *
 */
public class EuroSimulator<T extends EuroStrategy> extends TickaySimulator<T>{

	public EuroSimulator(Specification<T> spec){
		super(spec);
		this.expansionsRequired.add("EUROPE");
	}
	
	public EuroSimulationLog run() {
		
		Set<SpecErrorEntry> specErrors = new HashSet<SpecErrorEntry>();
		
		SpecErrorEntry its = this.validateIterations();
		if ( its != null ) specErrors.add( its );
		specErrors.addAll( this.validateExpansions() );

		// now, having checked expansions and iterations, produce the actual log
		EuroSimulationLog log = this.doGames();

		// attach log-level metadata to the log object (the game should already be there)
		log.setDateRun( this.makeDateStamp() );
		log.setSpecClassName( this.spec.getClass().getSimpleName() );
		log.setExpansions( expansions );
		log.getError().addAll( specErrors );
		
		return log;
	
	} // end method
	
	/**
	 * Returns <code>SimulationLog</code> for basic Europe game.
	 * <p>
	 * Generates errors that are added to returned log if strategies assigned are not <code>EuroStrategy</code> or if the 
	 * number of player for a given iteration is < 2 or > 5.
	 */
	public EuroSimulationLog doGames(){
		
		EuroSimulationLog rLog = new EuroSimulationLog();
		int totalPlayers = 0;
		List<EuroStrategy> strats; // wildcard fixes compilere error created by spec.assignStrategies(i) below
		SpecErrorEntry specError; // whatever error is currently occurring
		List<SpecErrorEntry> specErrors = rLog.getError(); // the log-level list of errors created by the specification
		String specName = this.spec.getClass().getSimpleName();
		
		for ( int i = 1; i <= this.iterations; i++ ) {
			
			strats = new ArrayList<>();
			
			for (T strat : spec.assignStrategies( i ) ){
				if ( strat instanceof EuroStrategy ){
					strats.add((EuroStrategy)strat);
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
					strats.add( new DefaultEuroStrategy() );
				}
			}
		
			EuroGame euroGame = new EuroGame( strats, this.expansions );
			
			rLog.getEuroGameEntry().add( euroGame.run() );
		}
		return rLog;
	}
	
}
