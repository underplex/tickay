package com.underplex.tickay.test;

import java.util.ArrayList;
import java.util.HashSet;
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
import com.underplex.tickay.game.Ticket;
import com.underplex.tickay.play.TicketChoicePlay;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Picker;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TicketPlayTests {

	
	@Test
	public void Draw3Tix() {
		
		// test of taking 3 tickets, finding appropriate play
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
		// System.out.println("After setup, discard + deck cards number: " + deckCards );
		Assert.assertEquals( deckCards, game.getTrains().drawSize() );

		Player player = game.getPlayers().turnOrder().get(0);
		
		Set<Ticket> topTix = new HashSet<>();
		
		for ( int i = 1; i <= 3; i++ ){
			topTix.add( game.getTickets().getShorts().get(0) );			
			game.getTickets().getShorts().remove(0);
		}
		
		// check that now that we've dealt 12 from original shorts deck, and removed 3 more, there are the correct number
		int remaining = 40 - (players * 3) - 3;
		Assert.assertEquals( remaining, game.getTickets().getShorts().size() );
		Assert.assertEquals( 3, topTix.size() );
		for ( Ticket tic : topTix ){
			System.out.println( tic + " has hashCode " + tic.hashCode() );
		}
		
		Set<TicketChoicePlay> options = PlayFinder.findTicketChoicePlays(game, player, topTix);
		System.out.println(topTix);
		
		for ( TicketChoicePlay tcp : options ){
			System.out.println( tcp );
		}
		
		Assert.assertEquals( 7, options.size() );
		
		// now choose a random one and execute it
		
		TicketChoicePlay choice = Picker.selectRandom( options );
		int held = player.getTickets().getTickets().size();
		int kept = choice.getKeep().size();
		int putBack = choice.getPutBack().size();
		
		choice.choose(game, player);
		
		// check that correct numbers of cards are in each place
		Assert.assertEquals( held + kept, player.getTickets().getTickets().size() );
		Assert.assertEquals( remaining + putBack, game.getTickets().getShorts().size() );
				
		// check that no cards from hand are in shorts
		for ( Ticket tic : player.getTickets().getTickets() ){
			Assert.assertEquals( false, game.getTickets().getShorts().contains( tic ) );
		}
	}
	
	@Test
	public void Draw2Tix() {
		
		System.out.println("Draw2Tix test");
		// test of taking 3 tickets, finding appropriate play
		int players = 4;
		
		List<EuroStrategy> strats = new ArrayList<>();
		for ( int i = 1; i <= players; i++)
			strats.add( new TestStrategy() );
		
		EuroGame euroGame = new EuroGame( strats, "Europe");
		
		euroGame.setup();

		Player player = euroGame.getPlayers().turnOrder().get(0);

		// artificially change game state so that there are only 2 tickets in the shorts deck
		
		List<Ticket> shorts = euroGame.getTickets().getShorts();
		
		while ( shorts.size() > 2 )
			shorts.remove(0);
		
		// now we behave like the game is normal and try to do a ticket play
		Set<Ticket> drawTix = new HashSet<>();
		
		while ( shorts.size() > 0 && drawTix.size() <= 3){
			drawTix.add( shorts.get(0));
			shorts.remove(0);
		}
		
		Assert.assertEquals( 2, drawTix.size() );
		Assert.assertEquals( 0, shorts.size() );
		
		for ( Ticket tic : drawTix )
			System.out.println( tic + " has hashCode " + tic.hashCode() );
		
		
		Set<TicketChoicePlay> options = PlayFinder.findTicketChoicePlays(euroGame, player, drawTix);
		System.out.println( options );
				
		Assert.assertEquals( 3, options.size() );
				
	}
	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
