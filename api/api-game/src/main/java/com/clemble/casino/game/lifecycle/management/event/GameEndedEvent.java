package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.GameState;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.management.outcome.OutcomeAware;

public interface GameEndedEvent extends GameManagementEvent, OutcomeAware {

    public GameState getState();

}
