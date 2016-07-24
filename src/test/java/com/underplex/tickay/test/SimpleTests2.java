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
import com.underplex.tickay.game.Route;
import com.underplex.tickay.jaxb.DeckDrawEntry;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.play.ClaimPlay;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Finder;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SimpleTests2 {

	@Test
	public void test() {
		
		// tests basic aspects of train card management
		int players = 4;
				
		List<EuroStrategy> strats = new ArrayList<>();
		for ( int i = 1; i <= players; i++)
			strats.add( new TestStrategy() );
		
		EuroGame euroGame = new EuroGame( strats, "Europe");
		
		Assert.assertEquals( 110, euroGame.getTrains().drawSize() );
		Assert.assertEquals( players, euroGame.getPlayers().size() );
		
		euroGame.setup();
		for ( Player p : euroGame.getPlayers().turnOrder() ){
			Assert.assertEquals( 4, p.getTickets().getTickets().size() );
			Assert.assertEquals( 4, p.getTrains().size() );
		}
		// there ought to be 5 faceups right now and 110 - (4 * number of player) deck/discard cards
		Assert.assertEquals( 5, euroGame.getTrains().getFaceUps().size() );
		Assert.assertEquals( false, euroGame.getTrains().hasNoFaceUps() );
		int deckCards = 110 - 5 - (players * 4); //110 to begin with, minus 5 faceups, minus 4 for each player
		System.out.println("After setup, discard + deck cards number: " + deckCards );
		Assert.assertEquals( deckCards, euroGame.getTrains().drawSize() );

		DeckDrawEntry entry;
		for ( int i = 1; i <= 89; i++ ){
			Assert.assertEquals( false, euroGame.getTrains().isEmpty() );
			entry = euroGame.getTrains().draw();
			Assert.assertEquals( deckCards - i, euroGame.getTrains().drawSize() );
			System.out.println("After draw number " + i +", deck + discard size is " + euroGame.getTrains().drawSize() );
			Assert.assertEquals( true, entry.isCardToDraw() );
		}

		Assert.assertEquals( true, euroGame.getTrains().isEmpty() );
		entry = euroGame.getTrains().draw();
		Assert.assertEquals( false, entry.isCardToDraw() ); // couldn't draw a card;
		Assert.assertEquals( null, entry.getTrainType() ); // since no card drawn, value for TrainType
	}

	@Test
	public void test1() {
		
		// tests the creation of very specific game play possibilities
		int players = 4;
		
		List<EuroStrategy> strats = new ArrayList<>();
		for ( int i = 1; i <= players; i++)
			strats.add( new TestStrategy() );
		
		EuroGame euroGame = new EuroGame( strats, "Europe");
		
		Assert.assertEquals( 110, euroGame.getTrains().drawSize() );
		Assert.assertEquals( players, euroGame.getPlayers().size() );
		
		euroGame.setup();
		for ( Player p : euroGame.getPlayers().turnOrder() ){
			Assert.assertEquals( 4, p.getTickets().getTickets().size() );
			Assert.assertEquals( 4, p.getTrains().size() );
		}
		// there ought to be 5 faceups right now and 110 - (4 * number of player) deck/discard cards
		Assert.assertEquals( 5, euroGame.getTrains().getFaceUps().size() );
		Assert.assertEquals( false, euroGame.getTrains().hasNoFaceUps() );
		int deckCards = 110 - 5 - (players * 4); //110 to begin with, minus 5 faceups, minus 4 for each player
		System.out.println("After setup, discard + deck cards number: " + deckCards );
		Assert.assertEquals( deckCards, euroGame.getTrains().drawSize() );
				
		Player player = euroGame.getPlayers().turnOrder().get(0);
		
		// Now, the player discards every card in hand
		while ( player.getTrains().size() > 0 ){
			euroGame.getTrains().toDiscard( player.getTrains().get(0) );
			player.getTrains().remove(0);
		}
		
		Assert.assertEquals( 4, euroGame.getTrains().discardSize() );
		Assert.assertEquals( 0, player.getTrains().size() );

		// now we add 3 blue cards to the game directly to the hand of player1
		player.getTrains().add( TrainType.BLUE);
		player.getTrains().add( TrainType.BLUE);
		player.getTrains().add( TrainType.BLUE);
		
		Assert.assertEquals( 3, player.getTrains().size() );
				
		Set<ClaimPlay> claims = PlayFinder.findClaimPlays(euroGame, player);
		// with exactly 3 blues in hand, a player can claim the following:
		
		Assert.assertEquals( 16, claims.size() );
//		for (Play c : claims){
//			System.out.println( c.toString() );
//		}	
		
		// check that the player's hand hasn't changed
		Assert.assertEquals( 3, player.getTrains().size() );

		ClaimPlay sofiaToConst = Finder.find( claims, "Sofia", "Const");
		Route sofiaConstRoute = Finder.find( euroGame.getBoard().getRoutes() , "sofia", "constant" );
		System.out.println( "Route: " + sofiaConstRoute.toString());
		System.out.println( "Claim: " + sofiaToConst.toString());
		
		Assert.assertEquals( 4, sofiaConstRoute.getPoints(euroGame, player) );
		Assert.assertEquals( 0, player.getPoints().count() );

		sofiaToConst.resolve(euroGame, player);
		
		Assert.assertEquals( sofiaConstRoute, sofiaToConst.getRoute() );
		Assert.assertEquals( 4, player.getPoints().count() );
		
		Assert.assertEquals( player, sofiaConstRoute.getBuilder() );
		Assert.assertEquals( TrainType.BLUE, sofiaConstRoute.getColor() );
		Assert.assertEquals( 0, sofiaConstRoute.getLocos() );
		Assert.assertEquals( false, sofiaConstRoute.isBuildable(player, euroGame) );
		Assert.assertEquals( true, sofiaConstRoute.isBuilt() );
		
	}
	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
