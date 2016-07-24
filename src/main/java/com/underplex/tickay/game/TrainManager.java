package com.underplex.tickay.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.info.TrainManagerInfo;
import com.underplex.tickay.jaxb.DeckDrawEntry;
import com.underplex.tickay.jaxb.TrainType;
import com.underplex.tickay.jaxb.WipeEntry;

/**
 * Represents the deck and positions of train cards in a game of Ticket to Ride.
 * @author Brandon Irvine
 */
public class TrainManager implements InfoSource<TrainManagerInfo> {

	private final List<TrainType> deck;
	private final List<TrainType> discard;
	private final List<TrainType> faceUps;
	private final Game game;
	private final TrainManagerInfo info;

	/**
	 * Primary constructor.
	 * @param expansions	<code>String</code> containing version and expansions used
	 * @param game			<code>Game</code> of Ticket to Ride using this manager
	 */
	public TrainManager( String expansions, Game game ) {
		this.game = game;
		this.deck = TrainFactory.makeDeck( expansions );
		Collections.shuffle( this.deck );
		this.discard = new ArrayList<>();
		this.faceUps = new ArrayList<>();
		this.info = new TrainManagerInfo( this );
	}

	public TrainManagerInfo info(){
		return this.info;
	}
	
	public List<TrainType> getFaceUps(){
		return this.faceUps;
	}
	
	/**
	 * Returns true if there are no cards in either deck or discard to draw.
	 */
	public boolean isEmpty(){
		return ( deck.isEmpty() && discard.isEmpty() );
	}
	
	/**
	 * Returns true if there are no cards face-up.
	 */
	public boolean hasNoFaceUps(){
		return ( faceUps.isEmpty() );
	}
	
	/**
	 * Returns List of entries representing any wipes that occurred during set-up.
	 * <p>
	 * The initial five draws aren't recorded as draws because they can't possibly cause reshuffle themselves.
	 */
	public List<WipeEntry> setUpFaceUps() {
		DeckDrawEntry c = null;
		for (int i = 1; i <= 5; i++ ){
			c = this.draw();
			this.faceUps.add( c.getTrainType() ); // add card but ignore the deck drawing b/c its implied by the setup
		}
		return checkForLocos(); // returns any wipes that happen at beginning of game
	}
	
	/**
	 * Returns DeckDrawEntry representing top card and removes from deck.
	 * <p>
	 * If there are no more cards to draw, the entry will return null for trainType.
	 */
	public DeckDrawEntry draw() {
		DeckDrawEntry entry = new DeckDrawEntry();
		if ( deck.size() > 0 ) {
			entry.setCardToDraw( true );
			entry.setReshuffle( false );
			entry.setTrainType( deck.get(0) );
			deck.remove(0);
		} else if ( deck.size() == 0 && discard.size() > 1 ) {
			entry.setCardToDraw( true );
			entry.setReshuffle( true );
			deck.addAll( discard );

			Collections.shuffle( deck );
			discard.clear();

			entry.setTrainType( deck.get(0) );
			deck.remove(0);

		// check for condition where there are no cards to draw whatsoever
		} else if ( this.isEmpty() ) {
			entry.setCardToDraw( false );
			entry.setTrainType( null );
			entry.setReshuffle( false );
		}
		return entry;
	} // end method

	/**
	 * Checks the face up cards for 3 locos; resets them if needed. Returns List of wipes in order they occurred.
	 */
	public List<WipeEntry> checkForLocos() {
		
		List<WipeEntry> rList = new ArrayList<>();
		
		int counter = 0; // represents number of times wipes have occurred
		
		boolean tooMany = ( Collections.frequency(faceUps, TrainType.LOCO) >= 3 );
		
		DeckDrawEntry draw;
		
		WipeEntry wipeEntry;
		do {

			if ( tooMany ) {
				
				discard.addAll( faceUps );
				faceUps.clear();
				
				wipeEntry = new WipeEntry();
				
				// try to turn up 5 (failed draws will just result in failed entries)
				for ( int i = 1; i <= 5; i++ ){
					draw = this.draw();
					// add card but ignore the deck drawing b/c its implied by the setup
					wipeEntry.getFaceUpReplacement().add( draw );
					if ( draw.getTrainType() != null )
						this.faceUps.add( draw.getTrainType() );
				}
				rList.add(wipeEntry);
			}

			tooMany = ( Collections.frequency(faceUps, TrainType.LOCO) >= 3 );
			counter++;

		} while ( tooMany && counter < 5 );
		
		// In the rare, crazy event there are too many locos in the deck to
		// produce face ups w/ < 3 locos, just put out no face ups.
		// In theory face ups should be put out again, but in practice this avoids an infinite loop 
		// caused by only having locos in deck, and it's not likely this will ever be a practical situation.
		// This event (breaking the deck/discard with too many wipes) is represented by an empty WipeEntry.
		if ( counter >= 5 ) {
			discard.addAll(faceUps);
			faceUps.clear();
			rList.add( new WipeEntry() ); // add empty, blank WipeEntry to show that this is just broken
		}
		return rList;
	} // end method

	/**
	 * Adds a <code>train</code> to the discard.
	 */
	public void toDiscard( TrainType train ){
		this.discard.add(train);
	}

	/**
	 * Returns the number of cards in the deck and discard combined (= number of cards that could be drawn).
	 */
	public int drawSize(){
		return this.deck.size() + this.discard.size();
	}
	
	/**
	 * Returns number of cards in deck.
	 */
	public int deckSize(){
		return this.deck.size();
	}
	/**
	 * Returns number of cards in discard.
	 */
	public int discardSize(){
		return this.discard.size();
	}

	/**
	 * Breaks rules of game to add a train card on top of deck.
	 */
	public void stackDeck(TrainType train){
		deck.add(0, train);
	}
}


