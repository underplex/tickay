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
import com.underplex.tickay.game.Payment;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.jaxb.DeckDrawEntry;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.jaxb.TunnelingEntry;
import com.underplex.tickay.play.ClaimPlay;
import com.underplex.tickay.play.Play;
import com.underplex.tickay.player.EuroPlayer;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tickay.util.PaymentCalculator;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Finder;
import com.underplex.tool.Picker;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ClaimPlayTests4 {

	@Test
	public void tunnelTest() {
		System.out.println( "*************************************");
		System.out.println( "begin tunnelTest method for ClaimPlayTests4");
		System.out.println( "*************************************");

		int players = 3;
		
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
				
		Player player = game.getPlayers().turnOrder().get(0);
		
		// Now, the player discards every card in hand
		while ( player.getTrains().size() > 0 ){
			game.getTrains().toDiscard( player.getTrains().get(0) );
			player.getTrains().remove(0);
		}
		
		Assert.assertEquals( 4, game.getTrains().discardSize() );
		Assert.assertEquals( 0, player.getTrains().size() );

		// now we add 3 blue cards to the game directly to the hand of player1
		player.getTrains().add( TrainType.WHITE);
		player.getTrains().add( TrainType.WHITE);
		
		Assert.assertEquals( 2, player.getTrains().size() );
				
		Set<ClaimPlay> claims = PlayFinder.findClaimPlays(game, player);
		
		System.out.println( "Claims found number: " + claims.size());
		for (Play c : claims){
			System.out.println( c.toString() );
		}
		Assert.assertEquals( 13, claims.size() );
		
		// check that the player's hand hasn't changed
		Assert.assertEquals( 2, player.getTrains().size() );

		ClaimPlay claim1 = Finder.find( claims, "London", "Dieppe");
		Assert.assertEquals( null, claim1 );
		
		claim1 = Finder.find( claims, "wien", "pest");

		System.out.println( "Claim: " + claim1.toString());
		
		Assert.assertEquals( 1, claim1.getRoute().getPoints(game, player) );
		Assert.assertEquals( 0, player.getPoints().count() );

		claim1.resolve(game, player);
		
		Assert.assertEquals( 1, player.getPoints().count() );
		Assert.assertEquals( 1, player.getTrains().size() );
		
		Assert.assertEquals( player, claim1.getRoute().getBuilder() );
		Assert.assertEquals( TrainType.WHITE, claim1.getRoute().getColor() );
		Assert.assertEquals( 0, claim1.getRoute().getLocos() );
		// Assert.assertEquals( false, claim1.getRoute().isBuildable(player, game) );
		Assert.assertEquals( true, claim1.getRoute().isBuilt() );
		
		// test of Stockholm-Petrograd w/ additional cards
		
		// Now, the player discards every card in hand
		while ( player.getTrains().size() > 0 ){
			game.getTrains().toDiscard( player.getTrains().get(0) );
			player.getTrains().remove(0);
		}
		
		Assert.assertEquals( 0, player.getTrains().size() );

		// now we add 3 blue cards to the game directly to the hand of player1
		player.getTrains().add( TrainType.GREEN);
		player.getTrains().add( TrainType.GREEN);
		player.getTrains().add( TrainType.GREEN);
		player.getTrains().add( TrainType.GREEN);
		player.getTrains().add( TrainType.GREEN);
		player.getTrains().add( TrainType.GREEN);
		player.getTrains().add( TrainType.GREEN);
		player.getTrains().add( TrainType.GREEN);
		player.getTrains().add( TrainType.GREEN);
		
		game.getTrains().stackDeck( TrainType.PURPLE );
		game.getTrains().stackDeck( TrainType.YELLOW );
		game.getTrains().stackDeck( TrainType.LOCO );
		
		claims = PlayFinder.findClaimPlays(game, player);
		
		System.out.println( "Claims found number: " + claims.size());
		for (Play c : claims)
			System.out.println( c.toString() );
		
		Assert.assertEquals( 32, claims.size() ); // notice this is the same as for 8 greens...because nothing is longer than 8 anyway
		
		// check that the player's hand hasn't changed
		Assert.assertEquals( 9, player.getTrains().size() );

		claim1 = Finder.find( claims, "holm", "petro");

		System.out.println( "Claim: " + claim1.toString());
		
		Assert.assertEquals( 21, claim1.getRoute().getPoints(game, player) );
		Assert.assertEquals( 1, player.getPoints().count() ); // 1 point from previous claim

		int initialDeckSize = game.getTrains().deckSize();
		int initialDiscSize = game.getTrains().discardSize();
		
		// before Petro-Stockholm is claimed
		// now we go step-by-step through the resolve tunnel method
		// first, to resolve tunnels, flip over cards
		Set<DeckDrawEntry> draws = claim1.doFlips(game, player);
		
		Assert.assertEquals( initialDeckSize - 3, game.getTrains().deckSize());
		
		// second, analyze what you've flipped over
		int additional = claim1.analyzeFlips( game, player, draws );
		Assert.assertEquals( 1, additional ); // all of the top-decked cards were Green, so the additional cost imposed should be 3
		
		// third, find payments
		EuroPlayer euroPlayer = null;
		EuroGame euroGame = null;
		if (player instanceof EuroPlayer)  euroPlayer = (EuroPlayer)player;
		if (game instanceof EuroGame)  euroGame = (EuroGame)game;
		Set<Payment> tunnelOptions = PaymentCalculator.findTunnelPayments( euroGame, euroPlayer, claim1.getPayment(), additional);
		// with 9 greens in hand, 8 greens would be spent for initial base payment and 1 more would be spent to pay the additional cost 
		// (i.e. the cost of 1 additional green created by flipping 1 green and 2 off colors) 
		Assert.assertEquals( 1, tunnelOptions.size() ); 
		
		Payment choice = Picker.selectRandom(tunnelOptions);
		
		claim1.resolveTunnelChoice( game, player, new TunnelingEntry(), choice );

		Assert.assertEquals( 22, player.getPoints().count() ); // 1 point from previous claim, 21 from claiming Petro-Stockholm
		Assert.assertEquals( 0, player.getTrains().size() );
		Route route1 = Finder.find(game.getBoard().getRoutes(), "Stockholm", "Petro");
		Assert.assertEquals( true, route1.isBuilt());
		Assert.assertEquals( player, route1.getBuilder());
		
		Assert.assertEquals( initialDiscSize + 3 + 1 + 8, game.getTrains().discardSize());
		
	}
	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}