package com.underplex.tickay.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.game.Path;
import com.underplex.tickay.player.Network;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tool.Finder;
import com.underplex.tool.Picker;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NetworkTest1 {

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
		
		Game game = new EuroGame( strats, "Europe");
		
		Assert.assertEquals( 110, game.getTrains().drawSize() );
		Assert.assertEquals( players, game.getPlayers().size() );
		
		game.setup();
		for ( Player p : game.getPlayers().turnOrder() ){
			Assert.assertEquals( 4, p.getTickets().getTickets().size() );
			Assert.assertEquals( 4, p.getTrains().size() );
		}
		
		// there ought to be 5 faceups right now and 110 - (4 * number of player) deck/discard cards
		Assert.assertEquals( 5, game.getTrains().getFaceUps().size() );
		Assert.assertEquals( false, game.getTrains().hasNoFaceUps() );
		int deckCards = 110 - 5 - (players * 4); //110 to begin with, minus 5 faceups, minus 4 for each player
		System.out.println("After setup, discard + deck cards number: " + deckCards );
		Assert.assertEquals( deckCards, game.getTrains().drawSize() );
				
		Player apu = game.getPlayers().turnOrder().get(0);
		Player barney = game.getPlayers().turnOrder().get(1);
		Player carl = game.getPlayers().turnOrder().get(2);
		Player duffman = game.getPlayers().turnOrder().get(3);	

		Assert.assertEquals(0, apu.getNetworks().getAll().size());
		apu.getRoutes().addRoute( Finder.find( game.getBoard().getRoutes(), "budapest","kyiv"));
		Assert.assertEquals(1, apu.getNetworks().getAll().size());
		Network net = Picker.selectRandom( apu.getNetworks().getAll() );
		Assert.assertEquals(1, net.getRoutes().size());
		Assert.assertEquals(true, net.contains(Finder.find( game.getBoard().getCities(), "kyiv") ) ); // does have Kyiv in this network
		Assert.assertEquals(false, net.contains(Finder.find( game.getBoard().getCities(), "curest") ) ); // doesn't have Bucuresti in this network
		apu.getRoutes().addRoute( Finder.find(game.getBoard().getRoutes(), "curest", "sevas")); // add route that doesn't connect to current network
		Assert.assertEquals(2, apu.getNetworks().getAll().size()); //... so now we have two isolated "networks" of one route apiece
		apu.getRoutes().addRoute( Finder.find(game.getBoard().getRoutes(), "curest", "kyiv")); // add route that connects two previous networks
		Assert.assertEquals(1, apu.getNetworks().getAll().size()); //...so now we have one network with 3 routes in it
		
		net = Picker.selectRandom( apu.getNetworks().getAll() ); // now, get that one single network
		
		List<Path> paths = new ArrayList<>( net.paths() );
		
		Collections.sort(paths);
		Collections.reverse(paths);
		
		Assert.assertEquals( false, paths.get(0).equals(paths.get(1)));
		
		System.out.println("Network is: " + net.toString() );
		System.out.println("********************");
		System.out.println("Paths listed beginning on following line:");
		for (Path path : paths){
			System.out.println(path.toString() );
		}
	}

	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
