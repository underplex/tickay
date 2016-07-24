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

import com.underplex.tickay.game.AmericaGame;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.game.Ticket;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.AmericaStrategy;
import com.underplex.tickay.strategy.DefaultAmericaStrategy;
import com.underplex.tool.Finder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AmericaInstanceTest1 {

	@Test
	public void test1() {

		System.out.println( "*************************************");
		System.out.println( this.getClass().getSimpleName() );
		System.out.println( "*************************************");

		// tests the creation of very specific game play possibilities
		int players = 5;
		
		List<AmericaStrategy> strats = new ArrayList<>();
		for ( int i = 1; i <= players; i++)
			strats.add( new DefaultAmericaStrategy() );
		
		Game game = new AmericaGame( strats, "America");
		
		Assert.assertEquals( 110, game.getTrains().drawSize() );
		Assert.assertEquals( players, game.getPlayers().size() );

		Assert.assertEquals( null, Finder.find( game.getBoard().getCities(), "Palermo" ));
		Assert.assertEquals( null, Finder.find( game.getBoard().getCities(), "petroGRAD" ));
		
		Assert.assertNotNull( Finder.find( game.getBoard().getCities(), "york" ));
		Assert.assertNotNull( Finder.find( game.getBoard().getCities(), "nash" ));
		Assert.assertNotNull( Finder.find( game.getBoard().getCities(), "pitts" ));
		Assert.assertNotNull( Finder.find( game.getBoard().getCities(), "WINNI" ));
		Assert.assertNotNull( Finder.find( game.getBoard().getCities(), "bosTON" ));
		Assert.assertNotNull( Finder.find( game.getBoard().getCities(), "rock" ));
		Assert.assertNotNull( Finder.find( game.getBoard().getCities(), "miami" ));
		Assert.assertNotNull( Finder.find( game.getBoard().getCities(), "denver" ));
		Assert.assertNotNull( Finder.find( game.getBoard().getCities(), "chicago" ));
		Assert.assertNotNull( Finder.find( game.getBoard().getCities(), "orleans" ));
		
		Assert.assertEquals( null, Finder.find( game.getBoard().getRoutes(), "Palermo", "Athina" ));
		Assert.assertEquals( null, Finder.find( game.getBoard().getRoutes(), "New York", "Madrid" ));
		Assert.assertEquals( null, Finder.find( game.getBoard().getRoutes(), "New York", "San Francisco" ));
		
		Assert.assertNotNull( Finder.find( game.getBoard().getRoutes(), "york", "boston" ));
		Assert.assertNotNull( Finder.find( game.getBoard().getRoutes(), "nash",  "LITTLE rock"));
		
		Assert.assertEquals( 0, game.getTickets().getLongs().size() );
		Assert.assertEquals( 0, game.getTickets().getOblivion().size() );

		Ticket tick1 = Finder.find( game.getTickets().getShorts(), "winnipeg", "rock" );
		Ticket tick2 = Finder.find( game.getTickets().getShorts(), "boston", "miami" );
		Ticket tick3 = Finder.find( game.getTickets().getShorts(), "denver", "PITTS" );
		Ticket tick4 = Finder.find( game.getTickets().getShorts(), "cago", "orLEANS" );
		Ticket tick5 = Finder.find( game.getTickets().getShorts(), "york", "orLEANS" );
		Ticket tick6 = Finder.find( game.getTickets().getShorts(), "winni", "chicago" );
		
		Assert.assertEquals( null, tick6 );
		Assert.assertEquals( null, tick5 );
		//TODO -- the following tests only work "sometimes" !!!
		
		Assert.assertEquals( 11, tick1.getPoints());
		Assert.assertEquals( 12, tick2.getPoints());
		Assert.assertEquals( 11, tick3.getPoints());
		Assert.assertEquals( 7, tick4.getPoints());

		
		game.setup();
		for ( Player player : game.getPlayers().turnOrder() ){
			Assert.assertEquals( 3, player.getTickets().getTickets().size() ); // ONLY 3 tickets to begin with in America version
			Assert.assertEquals( 4, player.getTrains().size() );
		}
		
		// there ought to be 5 faceups right now and 110 - (4 * number of player) deck/discard cards
		Assert.assertEquals( 5, game.getTrains().getFaceUps().size() );
		Assert.assertEquals( false, game.getTrains().hasNoFaceUps() );
		int deckCards = 110 - 5 - (players * 4); //110 to begin with, minus 5 faceups, minus 4 for each player
		System.out.println("After setup, discard + deck cards number: " + deckCards );
		Assert.assertEquals( deckCards, game.getTrains().drawSize() );
				
		Player apu = game.getPlayers().turnOrder().get(0);
		Player barney = game.getPlayers().turnOrder().get(1);
		Player carl = game.getPlayers().turnOrder().get(2);
		Player duffman = game.getPlayers().turnOrder().get(3);	
		Player edna = game.getPlayers().turnOrder().get(4);
		
		Set<Route> routes = game.getBoard().getRoutes();

		
	}
	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}	
	
}
