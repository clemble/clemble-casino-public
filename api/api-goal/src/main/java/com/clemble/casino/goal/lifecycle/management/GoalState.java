package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.lifecycle.management.State;
import com.clemble.casino.lifecycle.configuration.rule.time.PlayerClock;

/**
 * Created by mavarazy on 9/20/14.
 */
public class GoalState implements State {

    final private PlayerClock clock;

    public GoalState(PlayerClock clock) {
        this.clock = clock;
    }

    public PlayerClock getClock() {
        return clock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalState goalState = (GoalState) o;

        if (!clock.equals(goalState.clock)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return clock.hashCode();
    }

}
