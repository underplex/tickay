package com.underplex.tickay.play;

import java.util.HashSet;
import java.util.Set;

import com.underplex.tickay.game.Game;
import com.underplex.tickay.info.SecondTakePlayInfo;
import com.underplex.tickay.info.TakeTrainsPlayInfo;
import com.underplex.tickay.jaxb.DeckDrawEntry;
import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.jaxb.SingleTakeEntry;
import com.underplex.tickay.jaxb.StrategyErrorEntry;
import com.underplex.tickay.jaxb.StrategyErrorType;
import com.underplex.tickay.jaxb.TakeTrainsEntry;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.player.Player;
import com.underplex.tickay.util.PlayFinder;
import com.underplex.tool.Picker;

/**
 * Represents a legal option to take a specific train from face ups or from
 * deck.
 * <p>
 * A null value in trainType represents the option to pick a train blindly from
 * the deck.
 * <p>
 * This should only be instantiated when the game and player are in a state where this represents a legal move for <code>player</code>.
 */
public class TakeTrainsPlay implements Play {

	private TrainType trainType;
	private Player player;
	private TakeTrainsPlayInfo info;
	
	public TakeTrainsPlay( Player player, TrainType trainType ) {
		this.player = player;
		this.trainType = trainType;
		this.info = new TakeTrainsPlayInfo( this );
	}

	public PlayType getPlayType() {
		return PlayType.TAKE_TRAINS;
	}
	
	public String toString(){
		String card = (this.trainType == null ? "deck draw" : "face-up " + this.trainType.toString() );
		return ("PLAY: take trains, first " + card ); 
	}
	
	public TakeTrainsPlayInfo info(){
		return this.info;
	}
	
	/**
	 * Returns the type of train this would pick up. If <code>null</code> this would pick up a deck card.
	 */
	public TrainType getTrainType(){
		return this.trainType;
	}

	public TakeTrainsEntry resolve( Game game, Player player ) {
		TakeTrainsEntry rEntry = new TakeTrainsEntry();

		// first take is recorded
		rEntry.setTake1( resolveSingleTake( game, player, this.trainType ) ); 

		// if player didn't choose to take a loco from face-ups, and if there are any cards at all to take
		if ( trainType != TrainType.LOCO ) {
			// find legal options for taking a second card
			Set<SecondTakePlayInfo> options = PlayFinder.findSecondTakePlays( player.getGame(), player );
			// if there are no options, just record that in entry
			if ( options.size() == 0 ){
				rEntry.setTake2( null ); // no second take recorded
			//else if there are any options...
			} else {
				
				SecondTakePlayInfo choice;
				// if just one option, make that the choice
				if ( options.size() == 1){
					// player doesn't make decision, only does single available option
					choice = Picker.selectRandom( options ); // cheap way to choose single element of Set
				// if multiple options, player chooses then choice is validated
				} else {
					// have player choose
					Set<SecondTakePlayInfo> immutableOptions = new HashSet<>( options );
					// the strategy needs a particular type of game (because it reflects the specific game)
					choice = player.getStrategy().chooseSecondTake( game.info(), player.myInfo(), immutableOptions );
					
					// validate choice
					if ( !options.contains( choice ) ) {
						StrategyErrorEntry error = new StrategyErrorEntry();
						
						error.setStrategyClass( player.getStrategy().getClass().getSimpleName() );
						error.setStrategyError( StrategyErrorType.SECOND_CARD_CHOICE );
						
						rEntry.setError( error );
						
						// now replace illegal choice with legal one -- options has to be at least size 1 (implicitly and for this method)
						choice = Picker.selectRandom( options );
					}

				}
				rEntry.setTake2( TakeTrainsPlay.resolveSingleTake( game, player, choice.getTrainType() ) );
			}	
		}
		return rEntry;
	}

	/**
	 * Returns SingleTakeEntry that represents taking of a single card from face-ups or deck.
	 * <p>
	 * Assumes that trainType, if not null, is available in face-ups. If null, assumes that a card can be drawn.
	 */
	public static SingleTakeEntry resolveSingleTake( Game game, Player player, TrainType trainType ) {
		SingleTakeEntry singleTakeEntry = new SingleTakeEntry();
		singleTakeEntry.getFaceUpOffered().addAll( game.getTrains().getFaceUps() );

		if (trainType != null) { // taking one of the face-ups

			player.getTrains().add( trainType );
			game.getTrains().getFaceUps().remove( trainType );
			DeckDrawEntry draw = game.getTrains().draw();
		
			if ( draw.isCardToDraw() )
				game.getTrains().getFaceUps().add( draw.getTrainType() );
			
			singleTakeEntry.setFaceUpTaken( trainType );
			singleTakeEntry.setDeckTaken( null );
			singleTakeEntry.setFaceUpRevealed( draw );
			singleTakeEntry.getWipe().addAll( game.getTrains().checkForLocos() );

		} else if (trainType == null) { // choice to take a blind deck card

			DeckDrawEntry deckDraw = game.getTrains().draw(); // take card from deck, make entry
			
			player.getTrains().add( deckDraw.getTrainType() ); // add card to player's train hand
			
			singleTakeEntry.setFaceUpTaken( null );
			singleTakeEntry.setDeckTaken( deckDraw );
			
			singleTakeEntry.setFaceUpRevealed( null );
			
		} // end if-else for take

		return singleTakeEntry;
	}

}
