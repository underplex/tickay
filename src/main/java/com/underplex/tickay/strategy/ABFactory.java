package com.underplex.tickay.strategy;

/**
 * Interface for a factory that produces A and B versions of strategies.
 * @author Brandon Irvine
 *
 */
public interface ABFactory<E> {

	/**
	 * Returns a strategy designated A.
	 * @return
	 */
	E makeA();
	
	/**
	 * Returns a strategy designated B.
	 * @return
	 */
	E makeB();
	
}
