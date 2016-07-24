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

public class WipeTest1 {

	/**
	 * Tests the wiping of trains when there are 3 locos
	 */
	@Test
	public void test() {
				
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
				
		List<TrainType> faceUps = euroGame.getTrains().getFaceUps(); 
		// Now, we artificially replace the faceups
		faceUps.clear();
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.BLUE );
		faceUps.add( TrainType.LOCO );

		// and we artificially stack the deck
		euroGame.getTrains().stackDeck( TrainType.RED ); // the 7th card from the top
		euroGame.getTrains().stackDeck( TrainType.GREEN ); // the 6th card from the top
		euroGame.getTrains().stackDeck( TrainType.WHITE ); // the 5th card from the top
		euroGame.getTrains().stackDeck( TrainType.PURPLE ); // the 4th card from the top
		euroGame.getTrains().stackDeck( TrainType.ORANGE ); // the 3rd card from the top

		euroGame.getTrains().stackDeck( TrainType.LOCO ); // the second card from top
		euroGame.getTrains().stackDeck( TrainType.LOCO ); // the top card
		
		// now, the only choice is a blue, a loco, or a blind draw
		
		Player player = euroGame.getPlayers().turnOrder().get(0);

		// now we give the player a blank hand by forcing him to discard all cards artificially
		while ( player.getTrains().size() > 0 ){
			euroGame.getTrains().toDiscard( player.getTrains().get(0) );
			player.getTrains().remove(0);
		}
		
		Assert.assertEquals( 4, euroGame.getTrains().discardSize() );
		Assert.assertEquals( 0, player.getTrains().size() );

		// now we add 3 black cards to the game directly to the hand of player1
		player.getTrains().add( TrainType.BLACK);
		player.getTrains().add( TrainType.BLACK);
		player.getTrains().add( TrainType.BLACK);
		
		Assert.assertEquals( 3, player.getTrains().size() );
				
		Set<TakeTrainsPlay> trainPlays = PlayFinder.findTakeTrainPlays(euroGame, player);
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

		int deckSize = euroGame.getTrains().deckSize(); // before the resolve...
		
		TakeTrainsPlay.resolveSingleTake( euroGame, player, choice.getTrainType() );
		
		Assert.assertEquals( 4, player.getTrains().size() );
		Assert.assertEquals( 1, player.getTrains().count( TrainType.BLUE ) );
		Assert.assertEquals( 5, faceUps.size() ); // check that a new card was drawn and placed
		// at this point, there should be 3 BLUES and 2 LOCOS in the faceups
		Assert.assertEquals( 3, Collections.frequency( euroGame.getTrains().getFaceUps(), TrainType.BLUE ) ); 
		Assert.assertEquals( 2, Collections.frequency( euroGame.getTrains().getFaceUps(), TrainType.LOCO ) );
		Assert.assertEquals( deckSize - 1, euroGame.getTrains().deckSize() ); // deck should have drawn one card and put in faceups
				
		// now we consider the second cards that the player could take in this turn, given that he didn't choose a LOCO
		Set<SecondTakePlayInfo> options = PlayFinder.findSecondTakePlays( player.getGame(), player );
		Assert.assertEquals( 2, options.size() ); // the only two options are a blind draw and a draw of BLUE
		for (SecondTakePlayInfo stpi : options){
			System.out.println( stpi.toString() );
		}
		
		SecondTakePlayInfo secondTakeChoice = Finder.find( options, "BLUE");
			
		SingleTakeEntry entry = TakeTrainsPlay.resolveSingleTake( euroGame, player, secondTakeChoice.getTrainType() );
		// now a wipe should have happened, because the the replacement for the BLUE face-up taken would be a LOCO,
		// putting 3 locos in face-ups, which triggers a wipe
		// b/c we've stacked the deck, we know the 5 cards following the wipe won't include 3 locos
		Assert.assertEquals(1, entry.getWipe().size());
		WipeEntry wipe = entry.getWipe().get(0); // get the wipe entry created
		Assert.assertEquals( 1, Collections.frequency( euroGame.getTrains().getFaceUps(), TrainType.RED ) );
		Assert.assertEquals( 1, Collections.frequency( euroGame.getTrains().getFaceUps(), TrainType.WHITE ) );
		Assert.assertEquals( 1, Collections.frequency( euroGame.getTrains().getFaceUps(), TrainType.GREEN ) );
		Assert.assertEquals( 1, Collections.frequency( euroGame.getTrains().getFaceUps(), TrainType.PURPLE ) );
		Assert.assertEquals( 1, Collections.frequency( euroGame.getTrains().getFaceUps(), TrainType.ORANGE ) );
				
	}
	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
