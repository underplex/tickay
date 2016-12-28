package com.underplex.tickay.strategy;

public class AmBasicStrategyFactory implements ABFactory<AmericaStrategy>{

	public AmericaStrategy makeA(){
		return new DefaultAmericaStrategy();
	}
	
	public AmericaStrategy makeB(){
		return new DefaultAmericaStrategy();
	}
	
}
