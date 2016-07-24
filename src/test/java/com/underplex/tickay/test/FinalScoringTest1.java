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
import com.underplex.tickay.game.Path;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.jaxb.EuroFinalScoringEntry;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tickay.util.EuroScoreCalculator;
import com.underplex.tool.Finder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class FinalScoringTest1 {

	@Test
	public void test1() {

		System.out.println( "*************************************");
		System.out.println( "begin FinalScoringTest1");
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
		Player carl = game.getPlayers().turnOrder().get(2);
		Player duffman = game.getPlayers().turnOrder().get(3);	
		
		Set<Route> routes = game.getBoard().getRoutes();
		apu.getRoutes().addRoute( Finder.find( routes, "lisboa","madrid") );
		apu.getPoints().addPoints( 4 );

		Set<Path> paths;
		
		for ( Player p : game.getPlayers().turnOrder() ){
			System.out.println();
			System.out.println("Networks for player " + p.getPlayerType() + ":");
			System.out.println( p.getNetworks().getAll().toString());
			System.out.println();
			paths = p.getNetworks().paths();
			System.out.println("Paths for player " + p.getPlayerType() + ":");
			
			for (Path path : paths){
				System.out.println( path.toString());
				for (Path path2: paths){
					if ( path.equals(path2) && path != path2 )
						System.out.println("is the same as " + path2.toString());
				}
			}
			System.out.println();
		}
		
		EuroGame eg = null;
		if (game instanceof EuroGame ) eg = (EuroGame)game;
		EuroScoreCalculator calc = new EuroScoreCalculator( eg ); // instantiate helper class

		// calculate final scores assuming apu is 0, barney is 1, carl is 2, and duffman is 3 (since that's their turn order)
		List<EuroFinalScoringEntry> scores;
		
		// now, do calculations for final scoring
		scores = calc.finalScoring();
		
		Assert.assertEquals( 1, scores.get(0).getLongestTrack().size());
		Assert.assertEquals( 0, scores.get(1).getLongestTrack().size());
		Assert.assertEquals( 0, scores.get(2).getLongestTrack().size());
		Assert.assertEquals( 0, scores.get(3).getLongestTrack().size());
		
	}

	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
