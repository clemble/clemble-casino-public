package com.clemble.casino.lifecycle.configuration.rule.time;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 13/10/14.
 */
public class MovePlayerClock implements IPlayerClock {

    final public static MovePlayerClock DEFAULT = new MovePlayerClock(0, 0);

    final private long moveStart;
    final private long breachTime;

    @JsonCreator
    public MovePlayerClock(
        @JsonProperty("moveStart") long moveStart,
        @JsonProperty("breachTime")long breachTime) {
        this.moveStart = moveStart;
        this.breachTime = breachTime;
    }

    @Override
    public long getMoveStart() {
        return moveStart;
    }

    @Override
    public long getBreachTime() {
        return breachTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovePlayerClock)) return false;

        MovePlayerClock that = (MovePlayerClock) o;

        if (breachTime != that.breachTime) return false;
        if (moveStart != that.moveStart) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (moveStart ^ (moveStart >>> 32));
        result = 31 * result + (int) (breachTime ^ (breachTime >>> 32));
        return result;
    }

}
