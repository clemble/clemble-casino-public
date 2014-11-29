package com.clemble.casino.lifecycle.configuration.rule.time;

import java.io.Serializable;

import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.rule.breach.BreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.BreachPunishmentAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 24/12/13.
 */
public class PlayerClock implements BreachPunishmentAware, Serializable {

    /**
     * Generated 29/12/13
     */
    private static final long serialVersionUID = 8179910545586680100L;

    private long moveStart;
    private long timeSpent;
    private long breachTime;
    private long deadline;
    private BreachPunishment punishment;

    @JsonCreator
    public PlayerClock(
        @JsonProperty("moveStart") long moveStart,
        @JsonProperty("timeSpent") long timeSpent,
        @JsonProperty("breachTime") long breachTime,
        @JsonProperty("deadline") long deadline,
        @JsonProperty("punishment") BreachPunishment punishment) {
        this.moveStart = moveStart;
        this.breachTime = breachTime;
        this.deadline = deadline;
        this.timeSpent = timeSpent;
        this.punishment = punishment;
    }

    public long getMoveStart() {
        return moveStart;
    }

    public long getBreachTime() {
        return breachTime;
    }

    public long getDeadline() {
        return deadline;
    }

    public boolean wasBreached() {
        return System.currentTimeMillis() >= breachTime;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    @Override
    public BreachPunishment getPunishment() {
        return punishment;
    }

    public void deduce(long time) {
        this.timeSpent += time;
    }

    // TODO this is used as a hack, really bad practice
    public void start(long moveStart, long breachTime, long deadline, BreachPunishment breachPunishment) {
        if (this.moveStart == 0) {
            this.moveStart = moveStart;
            this.breachTime = breachTime;
            this.deadline = deadline;
            this.punishment = breachPunishment;
        }
    }

    public void stop() {
        if (moveStart != 0) {
            long add = System.currentTimeMillis() - moveStart;
            deduce(add > 0 ? add : 0);
            deadline = 0;
            this.moveStart = 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PlayerClock that = (PlayerClock) o;

        if (moveStart != that.moveStart)
            return false;
        if (timeSpent != that.timeSpent)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (timeSpent ^ (timeSpent >>> 32));
        result = 31 * result + (int) (moveStart ^ (moveStart >>> 32));
        return result;
    }

    public static PlayerClock create(Configuration configuration) {
        return new PlayerClock(0, 0, 0, 0, null);
    }
}
