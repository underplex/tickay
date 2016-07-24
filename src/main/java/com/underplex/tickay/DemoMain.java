package com.underplex.tickay;

import com.underplex.tickay.jaxb.AmericaSimulationLog;
import com.underplex.tickay.jaxb.EuroSimulationLog;
import com.underplex.tickay.jaxb.SimulationLog;
import com.underplex.tickay.simulator.AmericaSimulator;
import com.underplex.tickay.simulator.BasicAmerSpecImpl;
import com.underplex.tickay.simulator.BasicEuroSpecImpl;
import com.underplex.tickay.simulator.EuroSimulator;
import com.underplex.tickay.strategy.AmericaStrategy;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.util.FileIO;
import com.underplex.tool.Specification;

/**
 * A main class that demonstrates some of the functions of this library.
 * @author Brandon Irvine
 *
 */
public class DemoMain {
	
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
		
		// the log can now be examined as-is or exported to XML
		String fileLocation = FileIO.outputFile(log, "C:\\Users\\irvin_000\\workspace\\Tickay\\xml_output\\");
		
		// the XML can be imported back in...
		SimulationLog outLog = FileIO.inputFile(fileLocation);
		
		if ( outLog != null )
			System.out.println("JAXB files successfully imported back!");
		
		// do the same for the Europe version of the game
		
		// create specification for the simulator
		Specification<EuroStrategy> euroSpec = new BasicEuroSpecImpl( 1, "Europe", 4 );
				
		EuroSimulator<EuroStrategy> euroSim = new EuroSimulator<EuroStrategy>(euroSpec);
		EuroSimulationLog eurolog = euroSim.run();
		
		fileLocation = FileIO.outputFile(eurolog, "C:\\Users\\irvin_000\\workspace\\Tickay\\xml_output\\");
		
		SimulationLog euroLog = FileIO.inputFile(fileLocation);
		
		if ( euroLog != null )
			System.out.println("JAXB files successfully imported back!");
	}
}
