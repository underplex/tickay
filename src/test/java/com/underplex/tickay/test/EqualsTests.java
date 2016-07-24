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
import com.underplex.tickay.game.Game;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tool.Finder;
import com.underplex.tool.Picker;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EqualsTests {

	@Test
	public void test1() {
		
		System.out.println( "*************************************");
		System.out.println( "begin test1 method for EqualsTests");
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

		Player player = Picker.selectRandom( game.getPlayers().turnOrder() );
		
		// Now, the player discards every card in hand
		while ( player.getTrains().size() > 0 ){
			game.getTrains().toDiscard( player.getTrains().get(0) );
			player.getTrains().remove(0);
		}
		
		Assert.assertEquals( 4, game.getTrains().discardSize() );
		Assert.assertEquals( 0, player.getTrains().size() );

		// now we add 3 blue cards to the game directly to the hand of player1
		player.getTrains().add( TrainType.BLUE);
		player.getTrains().add( TrainType.BLUE);
		player.getTrains().add( TrainType.LOCO);
		
		// now check some basic equalities between routes
		Route lisboaCadiz = Finder.find(game.getBoard().getRoutes(), "lisboa", "cadiz");
		Route essenBerlin = Finder.find(game.getBoard().getRoutes(), "BeRlIn", "eSSen");
		
		Route lisboaCadiz2 = Finder.find(game.getBoard().getRoutes(), "lisboa", "cadiz");
		Route essenBerlin2 = Finder.find(game.getBoard().getRoutes(), "BeRlIn", "eSSen");
		// check for a few random false positives
		Assert.assertTrue( !lisboaCadiz2.equals(essenBerlin) );
		Assert.assertTrue( !lisboaCadiz2.equals(essenBerlin2) );
		Assert.assertTrue( !lisboaCadiz.equals(essenBerlin));

		
		// check for false negatives
		Assert.assertTrue( lisboaCadiz2.equals(lisboaCadiz) );
		Assert.assertTrue( lisboaCadiz.equals(lisboaCadiz2) );
		Assert.assertTrue( essenBerlin.equals(essenBerlin2) );
		Assert.assertTrue( essenBerlin2.equals(essenBerlin) );
		
		Route parisMar = Finder.find(game.getBoard().getRoutes(), "paris", "seille");
		Route bucuKyiv = Finder.find(game.getBoard().getRoutes(), "kYIV", "resti");

		// check against another random false positive
		Assert.assertTrue( !parisMar.equals(bucuKyiv));
		
		List<Route> routeList = new ArrayList<Route>( game.getBoard().getRoutes());
		
//		Collections.sort(routeList, Ordering.usingToString());
//		System.out.println( "Routes numbering..." + routeList.size());
		
		Route route1;
		Route route2;
		Route route3;
		Route route4;
		
		for ( Route route : routeList )
			System.out.println( route.toString());
		
		route1 = Finder.find(game.getBoard().getRoutes(), "berlin", "wars", "purple");
		route2 = Finder.find(game.getBoard().getRoutes(), "berlin", "wars", "orange"); // doesn't exist
		
		// check against another random false positive
		Assert.assertTrue( !route1.equals(route2));
		Assert.assertEquals( null, route2 );
		
		route1 = Finder.find(game.getBoard().getRoutes(), "MADRID", "pamp", "red");
		route2 = Finder.find(game.getBoard().getRoutes(), "LONA", "madrid", "white");
		route3 = Finder.find(game.getBoard().getRoutes(), "LONA", "madrid", "black");
		route4 = Finder.find(game.getBoard().getRoutes(), "black", "lona", "pamp", "madr");
		
		// check against another random false positive
		Assert.assertTrue( route1 == null );
		Assert.assertTrue( !route2.equals(route3));
		Assert.assertTrue( !route3.equals(route2));
		Assert.assertTrue( route4.equals(route3));
		Assert.assertTrue( route3.equals(route4));
		
		route1 = Finder.find(game.getBoard().getRoutes(), "london", "dieppe");
		route2 = route1.getAlternate();
		route3 = null;
		route4 = null;
		
		Assert.assertTrue( !route1.equals(route2) ); // since these are exactly alike, they will only fail equality if they're alternates
		Assert.assertTrue( !route2.equals(route1) ); // since these are exactly alike, they will only fail equality if they're alternates
	}

	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
