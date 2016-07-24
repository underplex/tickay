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

public class ClaimPlayTests {

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
		
		System.out.println("Print test");
		
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
				
		Player player = game.getPlayers().turnOrder().get(0);
		
		int preDiscard = game.getTrains().discardSize();
		// Now, the player discards every card in hand
		while ( player.getTrains().size() > 0 ){
			game.getTrains().toDiscard( player.getTrains().get(0) );
			player.getTrains().remove(0);
		}
		
		Assert.assertEquals( preDiscard + 4, game.getTrains().discardSize() );
		Assert.assertEquals( 0, player.getTrains().size() );

		// now we add 3 blue cards to the game directly to the hand of player1
		player.getTrains().add( TrainType.BLUE);
		player.getTrains().add( TrainType.BLUE);
		player.getTrains().add( TrainType.BLUE);
		
		Assert.assertEquals( 3, player.getTrains().size() );
				
		Set<ClaimPlay> claims = PlayFinder.findClaimPlays(game, player);
		// with exactly 3 blues in hand, a player can claim the following:
		
		// this is failing with 13
		Assert.assertEquals( 16, claims.size() );
		for (Play c : claims){
			System.out.println( c.toString() );
		}	
		
		// check that the player's hand hasn't changed
		Assert.assertEquals( 3, player.getTrains().size() );

		ClaimPlay sofiaToConst = Finder.find( claims, "Sofia", "Const");
		Route sofiaConstRoute = Finder.find( game.getBoard().getRoutes() , "sofia", "constant" );
		System.out.println( "Route: " + sofiaConstRoute.toString());
		System.out.println( "Claim: " + sofiaToConst.toString());
		
		Assert.assertEquals( 4, sofiaConstRoute.getPoints(game, player) );
		Assert.assertEquals( 0, player.getPoints().count() );

		sofiaToConst.resolve(game, player);
		
		Assert.assertEquals( sofiaConstRoute, sofiaToConst.getRoute() );
		Assert.assertEquals( 4, player.getPoints().count() );
		
		Assert.assertEquals( player, sofiaConstRoute.getBuilder() );
		Assert.assertEquals( TrainType.BLUE, sofiaConstRoute.getColor() );
		Assert.assertEquals( 0, sofiaConstRoute.getLocos() );
		Assert.assertEquals( false, sofiaConstRoute.isBuildable(player, game) );
		Assert.assertEquals( true, sofiaConstRoute.isBuilt() );
		
	}
	@Test
	public void locoTest() {
		System.out.println( "*************************************");
		System.out.println( "begin locoTest method for ClaimPlayTests");
		System.out.println( "*************************************");
		// small hand
		int players = 4;
		
		List<EuroStrategy> strats = new ArrayList<>();
		for ( int i = 1; i <= players; i++)
			strats.add( new TestStrategy() );
		
		Game game = new EuroGame( strats, "Europe");
		
		Assert.assertEquals( 110, game.getTrains().drawSize() );
		Assert.assertEquals( players, game.getPlayers().size() );
		
		game.setup();
		
		System.out.println("test message in locotest");
		
		for ( Player pPlayer : game.getPlayers().turnOrder() ){
			Assert.assertEquals( 4, pPlayer.getTickets().getTickets().size() );
			Assert.assertEquals( 4, pPlayer.getTrains().size() );
		}
		// there ought to be 5 faceups right now and 110 - (4 * number of player) deck/discard cards
		Assert.assertEquals( 5, game.getTrains().getFaceUps().size() );
		Assert.assertEquals( false, game.getTrains().hasNoFaceUps() );
		int deckCards = 110 - 5 - (players * 4); //110 to begin with, minus 5 faceups, minus 4 for each player
		System.out.println("After setup, discard + deck cards number: " + deckCards );
		Assert.assertEquals( deckCards, game.getTrains().drawSize() );
				
		Player euroPlayer = game.getPlayers().turnOrder().get(0);
		
		int beforeDiscard = game.getTrains().discardSize();
		
		// Now, the player discards every card in hand
		while ( euroPlayer.getTrains().size() > 0 ){
			game.getTrains().toDiscard( euroPlayer.getTrains().get(0) );
			euroPlayer.getTrains().remove(0);
		}
		
		Assert.assertEquals( beforeDiscard + 4, game.getTrains().discardSize() );
		Assert.assertEquals( 0, euroPlayer.getTrains().size() );

		euroPlayer.getTrains().add( TrainType.WHITE);
		euroPlayer.getTrains().add( TrainType.LOCO);
		
		Assert.assertEquals( 2, euroPlayer.getTrains().size() );
				
		Set<ClaimPlay> claims = PlayFinder.findClaimPlays(game, euroPlayer);
		
		System.out.println( "Claims found number: " + claims.size());
		for (Play c : claims){
			System.out.println( c.toString() );
		}
		Assert.assertEquals( 21, claims.size() );
		
		// check that the player's hand hasn't changed
		Assert.assertEquals( 2, euroPlayer.getTrains().size() );

		ClaimPlay claim1 = Finder.find( claims, "London", "Dieppe");
		System.out.println( "Claim: " + claim1.toString());
		
		Assert.assertEquals( 2, claim1.getRoute().getPoints(game, euroPlayer) );
		Assert.assertEquals( 0, euroPlayer.getPoints().count() );

		claim1.resolve(game, euroPlayer);
		
		Assert.assertEquals( 2, euroPlayer.getPoints().count() );
		
		Assert.assertEquals( euroPlayer, claim1.getRoute().getBuilder() );
		Assert.assertEquals( TrainType.GRAY, claim1.getRoute().getColor() );
		Assert.assertEquals( 1, claim1.getRoute().getLocos() );
		//Assert.assertEquals( false, route1.isBuildable(player, game) );
		Assert.assertEquals( true, claim1.getRoute().isBuilt() );
		
	}

	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
