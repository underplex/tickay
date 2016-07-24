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
import com.underplex.tickay.info.SecondTakePlayInfo;
import com.underplex.tickay.jaxb.SingleTakeEntry;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.jaxb.WipeEntry;
import com.underplex.tickay.play.TakeTrainsPlay;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Finder;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class WipeTest2 {

	/**
	 * Tests the wiping of trains when there are 3 locos
	 */
	@Test
	public void test() {
				
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
		// Now, we artificially replace the faceups so that they include the last three non-loco cards in the entire deck/discard
		faceUps.clear();
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.LOCO );
		faceUps.add( TrainType.LOCO );

		Player player = game.getPlayers().turnOrder().get(0);

		// now we give the player a blank hand by forcing him to discard all cards artificially
		while ( player.getTrains().size() > 0 ){
			game.getTrains().toDiscard( player.getTrains().get(0) );
			player.getTrains().remove(0);
		}
		
		// now, draw cards (and put them nowhere, effectively destroying them) until the deck and discard are completely empty
		while ( !game.getTrains().isEmpty() )
			game.getTrains().draw();
		
		// and now we artificially stack the deck with a ton of locos so that it will wipe 5 times then give up
		
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 

		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 

		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 

		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 

		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 
		game.getTrains().stackDeck( TrainType.LOCO ); 

		// now, the only choice is a blue, a loco, or a blind draw
	
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
		
		// we force the player to take a BLUE card from the faceups
		TakeTrainsPlay choice = Finder.find( trainPlays, "BLUE");

		int drawSize = game.getTrains().drawSize(); // before the resolve...
		
		// now we try to resolve getting a new face-up to replace the blue taken...
		// but this will "break" the deck because there are only 2 non-locos in the entire deck now
		// so the face-ups would continually be wiped and replaced infinite times, except we put an artificial limit of 
		// 5 wipes before the deck is treated as broken
		SingleTakeEntry entry1 = TakeTrainsPlay.resolveSingleTake( game, player, choice.getTrainType() );
		
		Assert.assertEquals(6, entry1.getWipe().size());// five regular wipes should have happened and one "empty" wipe representing a broken deck/discard
		WipeEntry wipe;
		
		for ( int i = 0; i <= 4; i++){
			wipe = entry1.getWipe().get(i); // get the last wipe entry created
			Assert.assertEquals( 5, wipe.getFaceUpReplacement().size() );
		}
		wipe = entry1.getWipe().get(5); // get the last wipe entry created
		Assert.assertEquals( 0, wipe.getFaceUpReplacement().size() );// this should have turned up no new faceups
		
		//checking other random aspects of this
		Assert.assertEquals( 4, player.getTrains().size() );
		Assert.assertEquals( 1, player.getTrains().count( TrainType.BLUE ) );
		// at this point, there should be no face-ups because the game has stopped providing them
		Assert.assertEquals( 0, faceUps.size() ); // check that a new card was drawn and placed
		// now we're in a weird situation where the drawSize has gone up because we're not putting out face-ups,
		// so the now there are 4 more cards in the deck/discard
		Assert.assertEquals( drawSize + 4, game.getTrains().drawSize() );
				
		// now we consider the second cards that the player could take in this turn, given that he didn't choose a LOCO
		Set<SecondTakePlayInfo> options = PlayFinder.findSecondTakePlays( player.getGame(), player );
		Assert.assertEquals( 1, options.size() ); // the only option is a blind draw
		for (SecondTakePlayInfo stpi : options){
			System.out.println( stpi.toString() );
		}
		
		SecondTakePlayInfo secondTakeChoice = Finder.find( options, "BLUE");
		Assert.assertEquals(null, secondTakeChoice);
		
		secondTakeChoice = Finder.find( options, "LOCO");
		Assert.assertEquals(null, secondTakeChoice);

	}
	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
