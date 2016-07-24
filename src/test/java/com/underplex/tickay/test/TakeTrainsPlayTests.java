package com.underplex.tickay.test;

import java.util.ArrayList;
import java.util.Collections;
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
import com.underplex.tickay.info.SecondTakePlayInfo;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.play.TakeTrainsPlay;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Finder;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TakeTrainsPlayTests {

	@Test
	public void test() {
		
		// tests the creation of very specific game play possibilities
		int players = 5;
		
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
				
		List<TrainType> faceUps = game.getTrains().getFaceUps(); 
		// Now, we artificially replace the faceups
		faceUps.clear();
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.LOCO );
		
		game.getTrains().stackDeck( TrainType.BLACK ); // puts a black on top of deck
		game.getTrains().stackDeck( TrainType.BLUE ); // puts a blue on top of deck (covering BLACK)
		
		// now, the only way for the player to get a black card this turn is by drawing two blind cards
		
		Player player = game.getPlayers().turnOrder().get(0);

		// now we give the player a blank hand by forcing him to discard all cards artificially
		while ( player.getTrains().size() > 0 ){
			game.getTrains().toDiscard( player.getTrains().get(0) );
			player.getTrains().remove(0);
		}
		
		Assert.assertEquals( 4, game.getTrains().discardSize() );
		Assert.assertEquals( 0, player.getTrains().size() );

		// now we add 3 black cards to the game directly to the hand of player1
		player.getTrains().add( TrainType.BLACK);
		player.getTrains().add( TrainType.BLACK);
		player.getTrains().add( TrainType.BLACK);
		
		Assert.assertEquals( 3, player.getTrains().size() );
				
		Set<TakeTrainsPlay> trainPlays = PlayFinder.findTakeTrainPlays(game, player);
		// there should really be only 3 play for taking trains: 
		// 1. taking a BLUE from faceups
		// 2. taking a blind draw from the deck 
		// 3. taking a LOCO from faceups
		
		Assert.assertEquals( 3, trainPlays.size() );
		for (TakeTrainsPlay tp : trainPlays){
			System.out.println( tp.toString() );
		}
		
		TakeTrainsPlay choice = Finder.find( trainPlays, "BLUE");
		

		int deckSize = game.getTrains().deckSize(); // before the resolve...
		
		TakeTrainsPlay.resolveSingleTake( game, player, choice.getTrainType() );
		
		Assert.assertEquals( 4, player.getTrains().size() );
		Assert.assertEquals( 1, player.getTrains().count( TrainType.BLUE ) );
		Assert.assertEquals( 5, faceUps.size() ); // check that a new card was drawn and placed
		// check with the new, drawn card there are 4 BLUE in the faceups
		Assert.assertEquals( 4, Collections.frequency( game.getTrains().getFaceUps(), TrainType.BLUE ) ); 
		Assert.assertEquals( deckSize - 1, game.getTrains().deckSize() ); // deck should have drawn one card and put in faceups
				
		// now we consider the second cards that the player could take in this turn, given that he didn't choose a LOCO
		Set<SecondTakePlayInfo> options = PlayFinder.findSecondTakePlays( player.getGame(), player );
		Assert.assertEquals( 2, options.size() ); // the only two options are a blind draw and a draw of blue
		for (SecondTakePlayInfo stpi : options){
			System.out.println( stpi.toString() );
		}
		
		SecondTakePlayInfo secondTakeChoice = Finder.find( options, "BLack");
		Assert.assertEquals( null, secondTakeChoice ); // Black isn't an option
		
		secondTakeChoice = Finder.find( options, "loco");
		Assert.assertEquals( null, secondTakeChoice ); // loco isn't an option

		secondTakeChoice = Finder.find( options, "blue");
		Assert.assertNotEquals( null, secondTakeChoice ); // loco isn't an option
		
		TakeTrainsPlay.resolveSingleTake( game, player, secondTakeChoice.getTrainType() );
				
	}
	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
