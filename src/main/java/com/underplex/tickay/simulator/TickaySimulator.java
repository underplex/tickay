package com.underplex.tickay.simulator;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.underplex.tickay.jaxb.SimulationLog;
import com.underplex.tickay.jaxb.SpecErrorEntry;
import com.underplex.tickay.jaxb.SpecErrorType;
import com.underplex.tool.Specification;


/**
 * Simulates game of Ticket to Ride and its expansions and versions, with <code>T</code> representing the strategy type for player.
 * @author Brandon Irvine
 */
public abstract class TickaySimulator<T>{

	protected Specification<T> spec;
	protected int iterations;
	protected String expansions;
	protected final int numPlayersDefault;
	protected final int iterationLimit; 
	protected final String specName;
	protected final Set<String> expansionsRequired;
	
	public TickaySimulator( Specification<T> spec, int numPlayersDefault, int iterationLimit){
		this.spec = spec;
		this.numPlayersDefault = numPlayersDefault;
		this.iterationLimit = iterationLimit;
		this.specName = spec.getClass().getSimpleName();
		this.expansionsRequired = new HashSet<>();
		this.expansions = "";
		this.iterations = 0;
	}

	public TickaySimulator( Specification<T> spec){
		this(spec,4,1000);
	}

	public Specification<T> getSpec() {
		return spec;
	}

	public void setSpec(Specification<T> spec) {
		this.spec = spec;
	}

	public abstract SimulationLog run();
	
	public abstract SimulationLog doGames();
	
	protected String makeDateStamp(){
		
		Calendar cal = Calendar.getInstance();
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		
		return year + "-" + month + "-" + day;
	}
	
	protected SpecErrorEntry validateIterations(){
			
		SpecErrorEntry specError = null;
		
		int check = spec.iterations(); // should only be consulted once
		
		if ( check > this.iterationLimit ){
			
			specError = new SpecErrorEntry();
			specError.setSpecClass( this.specName );
			specError.setSpecError(SpecErrorType.NUMBER_OF_ITERATIONS);
			
			this.iterations = this.iterationLimit;
		} else {
			this.iterations = check;
		}
		
		return specError;
	}
	
	protected Set<SpecErrorEntry> validateExpansions(){
	
		Set<SpecErrorEntry> specErrors = new HashSet<>();
		
		SpecErrorEntry specError;
		
		this.expansions += spec.expansions();
		
		for ( String exp : this.expansionsRequired ){
			if ( !expansions.toUpperCase().contains( exp ) ){
				specError = new SpecErrorEntry();
				specError.setSpecClass( this.specName );
				specError.setSpecError( SpecErrorType.EXPANSIONS );	
				specErrors.add( specError );
				this.expansions += exp; // quick fix because we currently only support Europe as of January 2016
			}
		}
		return specErrors;
	}
}
