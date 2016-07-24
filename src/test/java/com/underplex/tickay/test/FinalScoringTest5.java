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

import com.underplex.tickay.game.City;
import com.underplex.tickay.game.CityPair;
import com.underplex.tickay.game.EuroGame;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.game.Ticket;
import com.underplex.tickay.game.TicketFactory;
import com.underplex.tickay.jaxb.EuroFinalScoringEntry;
import com.underplex.tickay.jaxb.FinalScoringEntry;
import com.underplex.tickay.jaxb.TicketPointsEntry;
import com.underplex.tickay.player.EuroPlayer;
import com.underplex.tickay.player.Network;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.strategy.EuroStrategy;
import com.underplex.tickay.strategy.TestStrategy;
import com.underplex.tickay.util.EuroScoreCalculator;
import com.underplex.tickay.util.ScoreCalculator;
import com.underplex.tool.Finder;
import com.underplex.tool.Picker;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class FinalScoringTest5 {

	@Test
	public void test1() {

		System.out.println( "*************************************");
		System.out.println( this.getClass().getSimpleName() );
		System.out.println( "*************************************");

		// tests the creation of very specific game play possibilities
		int players = 5;
		
		List<EuroStrategy> strats = new ArrayList<>();
		for ( int i = 1; i <= players; i++)
			strats.add( new TestStrategy() );
		
		Game game = new EuroGame( strats, "Europe");
		
		Assert.assertEquals( 110, game.getTrains().drawSize() );
		Assert.assertEquals( players, game.getPlayers().size() );
		
		game.setup();
		for ( Player euroPlayer : game.getPlayers().turnOrder() ){
			Assert.assertEquals( 4, euroPlayer.getTickets().getTickets().size() );
			Assert.assertEquals( 4, euroPlayer.getTrains().size() );
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

		// create artificial tickets from the factory so we have complete control
		Set<Ticket> shorts = TicketFactory.makeEuropeShorts( game.getBoard().getCities());
		Set<Ticket> longs = TicketFactory.makeEuropeLongs( game.getBoard().getCities()); 
		Set<City> cities = new HashSet<>( game.getBoard().getCities() );
		
		apu.getTickets().getTickets().clear();
		barney.getTickets().getTickets().clear();
		carl.getTickets().getTickets().clear();
		duffman.getTickets().getTickets().clear();
		edna.getTickets().getTickets().clear();

		apu.getTickets().addTicket( Finder.find(longs, "lisboa", "danzig") );
		apu.getTickets().addTicket( Finder.find(shorts, "rostov", "smolensk") );
		
		apu.getRoutes().addRoute( Finder.find( routes, "lisboa","madrid") );
		apu.getRoutes().addRoute( Finder.find( routes, "Barcelona","madrid") );
		apu.getRoutes().addRoute( Finder.find( routes, "celona","seille") );
		apu.getRoutes().addRoute( Finder.find( routes, "roma","seille") );
		apu.getRoutes().addRoute( Finder.find( routes, "palermo","roma") );
		apu.getRoutes().addRoute( Finder.find( routes, "palermo","smyrna") );
		
		apu.getRoutes().addRoute( Finder.find( routes, "sofia","stantinople") );
		apu.getRoutes().addRoute( Finder.find( routes, "sofia","sarajevo") );
		apu.getRoutes().addRoute( Finder.find( routes, "budapest","sarajevo") );
		apu.getRoutes().addRoute( Finder.find( routes, "budapest","wien") );
		apu.getRoutes().addRoute( Finder.find( routes, "warsz","wien") );
		apu.getRoutes().addRoute( Finder.find( routes, "warsz","danzig") );

		barney.getRoutes().addRoute( Finder.find( routes, "smyrna","stantinople") );
		
		Assert.assertEquals( 2, apu.getNetworks().getAll().size() );
		
		Network net = Picker.selectRandom(apu.getNetworks().getAll());
		
		System.out.println("Net is comprised of following routes:");
		for (Route r : net.getRoutes()){
			System.out.println( r.toString() );
		}
		System.out.println("Net is comprised of following cities:");
		for (City c : net.getCities()){
			System.out.println( c.toString() );
		}
		System.out.println();
		
		CityPair pair1 = new CityPair( Finder.find(cities, "danzig"), Finder.find(cities, "lisboa") );
		
		Assert.assertEquals( false, apu.getNetworks().canConnect( pair1 ) ); // these are no connected
		
		EuroGame eg = null;
		if (game instanceof EuroGame) eg = (EuroGame)game;
		EuroScoreCalculator calc = new EuroScoreCalculator( eg ); // instantiate helper class

		// check that TicketPointsEntry are generated by TicketHandManager correctly
		List<TicketPointsEntry> ticEntries = apu.getTickets().makeEntries();
		Assert.assertEquals( 2, ticEntries.size() );
		
		List<EuroFinalScoringEntry> scores = calc.finalScoring();
				
		// get the only final scoring entry
		FinalScoringEntry apuScore = ScoreCalculator.findByPlayer(scores, apu);
		
		// find the lisboa-danzig ticket entry and the rostov-smolensk ticket entry
		TicketPointsEntry tpe1 = null;
		TicketPointsEntry tpe2 = null;
		for (TicketPointsEntry tpe : apuScore.getTicketPoints()){
			if ( tpe.getRoute().toUpperCase().contains("DANZIG") && tpe.getRoute().toUpperCase().contains("LISBOA")){
				tpe1 = tpe;
			} else if ( tpe.getRoute().toUpperCase().contains("ROSTOV") && tpe.getRoute().toUpperCase().contains("SMOLENSK") ) {
				tpe2 = tpe;
			}			
		}
		
		// check that it's completed
		Assert.assertEquals(false, tpe1.isCompleted()); // this ticket is no longer completed
		Assert.assertEquals(false, tpe2.isCompleted()); // this never was, still isn't completed
		
		// now...we add a station that helps apu finish
		EuroPlayer euroApu = null;
		if (apu instanceof EuroPlayer) euroApu = (EuroPlayer)apu;
		euroApu.getStations().built().add( Finder.find(cities, "constant") );
		Finder.find(cities, "constant").placeStation( euroApu );
		
		// now designate the route that the station will cover
		apu.getNetworks().addStationDecision( Finder.find(cities, "Constant"), Finder.find(routes, "constan","smyrna") );

		Assert.assertEquals( true, apu.getNetworks().canConnect( pair1 ) ); // these are no connected
		
		eg = null;
		if (game instanceof EuroGame) eg = (EuroGame)game;
		calc = new EuroScoreCalculator( eg ); // instantiate helper class

		scores = calc.finalScoring();
		
		// get the only final scoring entry
		apuScore = scores.get(0);
		
		// find the lisboa-danzig ticket entry
		tpe1 = null;
		tpe2 = null;
		for (TicketPointsEntry tpe : apuScore.getTicketPoints()){
			if ( tpe.getRoute().toUpperCase().contains("DANZIG") && tpe.getRoute().toUpperCase().contains("LISBOA")){
				tpe1 = tpe;
			} else if ( tpe.getRoute().toUpperCase().contains("ROSTOV") && tpe.getRoute().toUpperCase().contains("SMOLENSK") ) {
				tpe2 = tpe;
			}			
		}
		
		// check that it's completed
		Assert.assertEquals(true, tpe1.isCompleted()); // this ticket is no longer completed
		Assert.assertEquals(false, tpe2.isCompleted());		
	}
	
	@Before
	public void setUp() {
	}

	
	@After
	public void tearDown() {		
	}
	
	
}
