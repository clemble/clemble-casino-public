package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.GameContext;
import com.clemble.casino.game.lifecycle.management.GamePlayerContext;
import com.clemble.casino.game.lifecycle.management.GameState;
import com.clemble.casino.game.lifecycle.management.outcome.GameOutcome;

public interface GameEndedEvent extends GameManagementEvent {

    public GameState getState();

    public GameOutcome getOutcome();

}
