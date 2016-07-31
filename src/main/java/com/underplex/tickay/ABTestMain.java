package com.underplex.tickay;

import java.util.Set;

import com.underplex.tickay.analysis.Scorecard;
import com.underplex.tickay.analysis.Scorer;
import com.underplex.tickay.jaxb.AmericaSimulationLog;
import com.underplex.tickay.simulator.AmericaSimulator;
import com.underplex.tickay.simulator.BasicAmerSpecImpl;
import com.underplex.tickay.strategy.AmericaStrategy;
import com.underplex.tool.Specification;

/**
 * A main class that demonstrates some of the functions of this library.
 * @author Brandon Irvine
 *
 */
public class ABTestMain {
	
	public static void main(String[] args) {
		
		System.out.println("Demo for running simulations and producing XML results, "
				+ "then importing the results back in as Java class instances.");
		
		// do it for America
		// create specification for the simulator
		Specification<AmericaStrategy> spec = new BasicAmerSpecImpl( 100, "America", 4 );
		
		// create a new simulator based on the specification
		AmericaSimulator<AmericaStrategy> sim = new AmericaSimulator<AmericaStrategy>(spec);
		
		// actually run the simulation
		AmericaSimulationLog log = sim.run();
	
		Set<Scorecard> cards = Scorer.findAmScoreCards(log.getAmericaGameEntry());
		
		for (Scorecard sc: cards)
			sc.printOut();
		
		}
	
		
}
