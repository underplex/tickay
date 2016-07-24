package com.underplex.tickay.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.underplex.tickay.play.ClaimPlay;
import com.underplex.tickay.play.Play;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Finder;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ClaimPlayTests8 {

	@Test
	public void test1() {

		System.out.println( "*************************************");
		System.out.println( "begin" + this.getClass().getSimpleName() );
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
				
		// Now, the player discards every card in hand
		while ( apu.getTrains().size() > 0 ){
			game.getTrains().toDiscard( apu.getTrains().get(0) );
			apu.getTrains().remove(0);
		}
		
		Assert.assertEquals( 4, game.getTrains().discardSize() );
		Assert.assertEquals( 0, apu.getTrains().size() );

		// now we add 3 blue cards to the game directly to the hand of player1
		apu.getTrains().add( TrainType.WHITE);
		apu.getTrains().add( TrainType.WHITE);
		apu.getTrains().add( TrainType.WHITE);
		apu.getTrains().add( TrainType.ORANGE);
		apu.getTrains().add( TrainType.ORANGE);
		apu.getTrains().add( TrainType.ORANGE);
		
		Assert.assertEquals( 6, apu.getTrains().size() );
				
		Set<ClaimPlay> claims = PlayFinder.findClaimPlays(game, apu);
		
		Assert.assertEquals( 35, claims.size() );
		for (Play c : claims){
			System.out.println( c.toString() );
		}	
		
		// check that the player's hand hasn't changed
		Assert.assertEquals( 6, apu.getTrains().size() );

		ClaimPlay claim1 = Finder.find( claims, "paris", "frankfurt", "white");
		Route route1 = Finder.find( game.getBoard().getRoutes(), "paris", "frankfurt", "white");
		Route route2 = Finder.find( game.getBoard().getRoutes(), "paris", "frankfurt", "orange");

		System.out.println( claim1.toString() );
		System.out.println( route1.toString() );
		System.out.println( route2.toString() );
		
		claim1.resolve( game, apu );
		
		Assert.assertEquals( 4, apu.getPoints().count() );
		
		// testing alternate routes
		
		Assert.assertEquals( apu, route1.getBuilder() );
		Assert.assertEquals( TrainType.WHITE, route1.getColor() );
		Assert.assertEquals( 0, route1.getLocos() );
		Assert.assertEquals( false, route1.isBuildable(apu, game) );
		Assert.assertEquals( true, route1.isBuilt() );
		
		Assert.assertEquals( route1, route2.getAlternate() );
		Assert.assertEquals( route2, route1.getAlternate() );
		
		Assert.assertEquals( false, route2.isBuilt() );
		Assert.assertEquals( null, route2.getBuilder() );
		Assert.assertEquals( false, route2.isBuildable( apu, game ) );
		Assert.assertEquals( true, route2.isBuildable( barney, game ) );
	}
	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
