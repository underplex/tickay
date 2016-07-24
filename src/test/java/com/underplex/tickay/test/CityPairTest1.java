package com.underplex.tickay.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.underplex.tickay.game.City;
import com.underplex.tickay.game.CityPair;
import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tool.Finder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CityPairTest1 {

	@Test
	public void test1() {

		System.out.println( "*************************************");
		System.out.println( "begin " + this.getClass().getSimpleName());
		System.out.println( "*************************************");

		// tests the creation of very specific game play possibilities
		int players = 4;
		
		List<EuroStrategy> strats = new ArrayList<>();
		for ( int i = 1; i <= players; i++)
			strats.add( new TestStrategy() );
		
		Game euroGame = new EuroGame( strats, "Europe");
		
		Assert.assertEquals( 110, euroGame.getTrains().drawSize() );
		Assert.assertEquals( players, euroGame.getPlayers().size() );
		
		euroGame.setup();
		for ( Player euroPlayer : euroGame.getPlayers().turnOrder() ){
			Assert.assertEquals( 4, euroPlayer.getTickets().getTickets().size() );
			Assert.assertEquals( 4, euroPlayer.getTrains().size() );
		}
		
		// there ought to be 5 faceups right now and 110 - (4 * number of player) deck/discard cards
		Assert.assertEquals( 5, euroGame.getTrains().getFaceUps().size() );
		Assert.assertEquals( false, euroGame.getTrains().hasNoFaceUps() );
		int deckCards = 110 - 5 - (players * 4); //110 to begin with, minus 5 faceups, minus 4 for each player
		System.out.println("After setup, discard + deck cards number: " + deckCards );
		Assert.assertEquals( deckCards, euroGame.getTrains().drawSize() );
				
		Player apu = euroGame.getPlayers().turnOrder().get(0);
		Player barney = euroGame.getPlayers().turnOrder().get(1);
		Player carl = euroGame.getPlayers().turnOrder().get(2);
		Player duffman = euroGame.getPlayers().turnOrder().get(3);	

		City city1 = Finder.find( euroGame.getBoard().getCities(), "lisboa");
		City city2 = Finder.find( euroGame.getBoard().getCities(), "petrograd");
		City city3 = Finder.find( euroGame.getBoard().getCities(), "lisboa");

		CityPair pair1 = new CityPair(city1, city2);
		CityPair pair2 = new CityPair( pair1 );
		
		Assert.assertEquals(true, pair1.getCity1().equals(pair2.getCity1()));
		Assert.assertEquals(true, pair2.getCity2().equals(pair2.getCity2()));

		// test of whether same-city "pair" is equal to hetero-pair CityPair
		Assert.assertEquals(true, pair2.equals(pair1));
		
		pair1 = new CityPair(city1, city3);
		pair2 = new CityPair(city1, city2 );
	
		Assert.assertEquals(false, pair2.equals(pair1));
		
		pair1 = new CityPair(city1, city2);
		pair2 = new CityPair(city2, city1);
		
		Assert.assertEquals(true, pair2.equals(pair1));
	}

	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
