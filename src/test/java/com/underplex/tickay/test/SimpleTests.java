package com.underplex.tickay.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SimpleTests {

	@Test
	public void test() {
		
		List<EuroStrategy> strats = new ArrayList<>();
		for ( int i = 1; i <= 4; i++)
			strats.add( new TestStrategy() );
		
		EuroGame euroGame = new EuroGame( strats, "Europe");
		
		Assert.assertEquals( 4 , euroGame.getPlayers().size() );
		
	}

	@Test
	public void test1() {
		
	}

	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
