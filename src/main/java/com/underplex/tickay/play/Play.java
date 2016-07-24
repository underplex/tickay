package com.underplex.tickay.play;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.game.Game;
import com.underplex.tickay.info.PlayInfo;
import com.underplex.tickay.jaxb.PlayType;
import com.underplex.tickay.player.Player;

/**
 * An implementing instance represents one of the initial options to play in a turn.
 */
public interface Play extends InfoSource<PlayInfo>{
  
  /**
   * Resolves this play and returns object representing a record of the play.
   */
  Object resolve( Game game, Player player );

  PlayType getPlayType();
  
}
