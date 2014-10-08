package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.GameContext;
import com.clemble.casino.game.lifecycle.management.GamePlayerContext;
import com.clemble.casino.game.lifecycle.management.outcome.GameOutcome;

public interface GameEndedEvent<GPC extends GamePlayerContext> extends GameEvent {

    public GameContext<GPC> getContext();

    public GameOutcome getOutcome();

}
