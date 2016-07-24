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

import com.underplex.tickay.game.City;
import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.play.Play;
import com.underplex.tickay.play.StationPlay;
import com.underplex.tickay.player.EuroPlayer;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Finder;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StationPlayTests {

	@Test
	public void test1() {

		System.out.println( "*************************************");
		System.out.println( "begin StationPlayTests");
		System.out.println( "*************************************");

		// tests the creation of very specific game play possibilities
		int players = 5;
		
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
		EuroPlayer euroPlayer = null;
		
		if ( player instanceof EuroPlayer )  euroPlayer = (EuroPlayer)player;
		
		// Now, the player discards every card in hand
		while ( player.getTrains().size() > 0 ){
			euroGame.getTrains().toDiscard( player.getTrains().get(0) );
			player.getTrains().remove(0);
		}
		
		Assert.assertEquals( 4, euroGame.getTrains().discardSize() );
		Assert.assertEquals( 0, player.getTrains().size() );

		// now we add 3 blue cards to the game directly to the hand of player1
		euroPlayer.getTrains().add( TrainType.BLUE);
		euroPlayer.getTrains().add( TrainType.BLUE);
		
		euroPlayer.getTrains().add( TrainType.GREEN);
		euroPlayer.getTrains().add( TrainType.GREEN);
		
		euroPlayer.getTrains().add( TrainType.PURPLE);
		
		euroPlayer.getTrains().add( TrainType.LOCO);
			
		Assert.assertEquals( 6, player.getTrains().size() );
		
		Set<StationPlay> placements = PlayFinder.findStationPlays(euroGame, euroPlayer);

		// With no stations played already, one play for each type of card in hand FOR EACH city (no cities have a station at this point)
		int num = 4 * euroGame.getBoard().getCities().size();
		Assert.assertEquals( num, placements.size() );
	
		for (Play c : placements){
			System.out.println( c.toString() );
		}
		
		// check that the player's hand hasn't changed
		Assert.assertEquals( 6, player.getTrains().size() );

		StationPlay play1; 
		
		play1 = Finder.find( placements, "1 Purple", "BUCUR");
		
		play1.resolve(euroGame, player);

		Assert.assertEquals( 5, player.getTrains().size() );
		City city1;
		city1 = Finder.find( euroGame.getBoard().getCities(), "resti");
		Assert.assertEquals( true, city1.hasStation());
		
		// at this point, one station has been put down

		placements = PlayFinder.findStationPlays( euroGame, euroPlayer );
		play1 = Finder.find( placements, "1 Purple", "Zurich");
		Assert.assertEquals( null, play1 ); // 1 purple isn't a viable payment for Zurich
		
		play1 = Finder.findExcluding("1 loco", placements, "1 green", "Zurich");
		Assert.assertEquals( null, play1 ); // 1 green isn't a viable payment for Zurich

		play1 = Finder.find( placements, "2 green", "Bucur");
		Assert.assertEquals( null, play1 ); // Bucuresti is already taken up

		play1 = Finder.find( placements, "2 green", "Zurich");
		
		play1.resolve(euroGame, player);

		Assert.assertEquals( 3, player.getTrains().size() );
		
		city1 = Finder.find( euroGame.getBoard().getCities(), "zurich");
		Assert.assertEquals( true, city1.hasStation());
		city1 = Finder.find( euroGame.getBoard().getCities(), "paris");
		Assert.assertEquals( false, city1.hasStation());		
		Assert.assertEquals( 1, euroPlayer.getStations().stationsLeft());		

		// at this point, two stations have been put down
		
		placements = PlayFinder.findStationPlays(euroGame, euroPlayer);
		play1 = Finder.findExcluding( "2 blue", placements, "1 Loco", "Petrograd");
		Assert.assertEquals( null, play1 ); // 1 blue, 1 loco isn't a viable payment for petrograd

		play1 = Finder.findExcluding( "1 loco", placements, "2 blue", "Petrograd");
		Assert.assertEquals( null, play1 ); // 2 blue, 0 loco isn't a viable payment for petrograd
		
		play1 = Finder.find(placements, "2 blue", "1 loco", "Zurich");
		Assert.assertEquals( null, play1 ); // Zurich is already built

		play1 = Finder.find( placements, "2 blue", "1 loco", "Bucur");
		Assert.assertEquals( null, play1 ); // Bucuresti is already taken up

		play1 = Finder.find( placements, "2 blue", "1 loco", "petrograd");

		play1.resolve(euroGame, player);

		Assert.assertEquals( 0, player.getTrains().size() );
		
		city1 = Finder.find( euroGame.getBoard().getCities(), "petrograd");
		Assert.assertEquals( true, city1.hasStation());
		city1 = Finder.find( euroGame.getBoard().getCities(), "london");
		Assert.assertEquals( false, city1.hasStation()); // check for false positives		
		Assert.assertEquals( 0, euroPlayer.getStations().stationsLeft());		
		
		// now all three stations are build and no more can be, so no placements should be possible...
		placements = PlayFinder.findStationPlays(euroGame, euroPlayer);
		Assert.assertEquals( 0, placements.size());
	}

	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
