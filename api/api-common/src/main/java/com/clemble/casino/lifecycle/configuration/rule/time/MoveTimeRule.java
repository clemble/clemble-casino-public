package com.clemble.casino.lifecycle.configuration.rule.time;

import java.util.Date;

import com.clemble.casino.event.action.PlayerDefaultAction;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.MoveTimeoutSurrenderAction;
import com.clemble.casino.player.event.PlayerEvent;
import com.clemble.casino.lifecycle.configuration.rule.breach.BreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.LooseBreachPunishment;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("moveTime")
public class MoveTimeRule implements TimeRule {

    /**
     * Generated 09/04/13
     */
    private static final long serialVersionUID = -2949008185370674021L;

    final private BreachPunishment punishment;
    final private long limit;

    @JsonCreator
    public MoveTimeRule(@JsonProperty("limit") long limit, @JsonProperty("punishment") BreachPunishment punishment) {
        this.limit = limit;
        this.punishment = punishment;
    }

    public BreachPunishment getPunishment() {
        return punishment;
    }

    @Override
    public long timeUntilBreach(long totalTimeSpent) {
        return limit - totalTimeSpent;
    }

    @Override
    public long timeUntilBreach(PlayerClock clock) {
        return clock.getMoveStart() == 0 ? Long.MAX_VALUE : timeUntilBreach(System.currentTimeMillis() - clock.getMoveStart());
    }

    @Override
    public Date breachDate(PlayerClock clock) {
        return clock.getMoveStart() == 0 ? new Date(Long.MAX_VALUE) : new Date(clock.getMoveStart() + limit); 
    }

    @Override
    public PlayerAction toTimeBreachedEvent(String player) {
        if (punishment instanceof LooseBreachPunishment) {
            return new MoveTimeoutSurrenderAction(player);
        } else {
            return new PlayerDefaultAction(player);
        }
    }

    @Override
    public long getLimit() {
        return limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoveTimeRule that = (MoveTimeRule) o;

        if (limit != that.limit) return false;
        if (!punishment.equals(that.punishment)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = punishment.hashCode();
        result = 31 * result + (int) (limit ^ (limit >>> 32));
        return result;
    }

}
