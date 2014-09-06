package com.clemble.casino.game;

import com.clemble.casino.event.Event;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.action.ClientGameEvent;

/**
 * Created by mavarazy on 09/03/14.
 */
public interface RoundGameState extends GameState<RoundGameContext, PlayerAwareEvent> {
}
