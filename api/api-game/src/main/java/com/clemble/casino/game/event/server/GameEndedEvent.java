package com.clemble.casino.game.event.server;

import com.clemble.casino.event.GameEvent;
import com.clemble.casino.game.GameContext;
import com.clemble.casino.game.outcome.GameOutcome;

public interface GameEndedEvent extends GameEvent {

    public GameContext getContext();

    public GameOutcome getOutcome();

}
