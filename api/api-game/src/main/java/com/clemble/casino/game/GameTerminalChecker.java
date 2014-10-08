package com.clemble.casino.game;

import com.clemble.casino.game.lifecycle.management.outcome.GameOutcome;
import com.clemble.casino.game.lifecycle.management.unit.GameUnit;

/**
 * Created by mavarazy on 22/12/13.
 */
public interface GameTerminalChecker<T extends GameUnit> {

    public GameOutcome toOutcome(T unit);

    public boolean outcomePossible(T unit);

}
