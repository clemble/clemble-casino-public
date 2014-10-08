package com.clemble.casino.lifecycle.configuration.rule.time;

import java.util.Date;

import com.clemble.casino.event.action.PlayerDefaultAction;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.TotalTimeoutSurrenderAction;
import com.clemble.casino.player.event.PlayerEvent;
import com.clemble.casino.lifecycle.configuration.rule.breach.BreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.BreachPunishmentAware;
import com.clemble.casino.lifecycle.configuration.rule.breach.LooseBreachPunishment;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("totalTime")
public class TotalTimeRule implements TimeRule, BreachPunishmentAware {

    /**
     * Generated 09/04/13
     */
    private static final long serialVersionUID = 7452918511506230595L;

    final private long limit;
    final private BreachPunishment punishment;

    @JsonCreator
    public TotalTimeRule(@JsonProperty("limit") long limit, @JsonProperty("punishment") BreachPunishment punishment) {
        this.limit = limit;
        this.punishment = punishment;
    }

    @Override
    public BreachPunishment getPunishment() {
        return punishment;
    }

    @Override
    public long getLimit() {
        return limit;
    }

    @Override
    public long timeUntilBreach(long totalTimeSpent) {
        return limit - totalTimeSpent;
    }

    @Override
    public long timeUntilBreach(PlayerClock clock) {
        return clock.getMoveStart() == 0 ? Long.MAX_VALUE : timeUntilBreach((System.currentTimeMillis() - clock.getMoveStart()) + clock.getTimeSpent());
    }

    @Override
    public Date breachDate(PlayerClock clock) {
        return clock.getMoveStart() == 0 ? new Date(Long.MAX_VALUE) : new Date(clock.getMoveStart() + limit - clock.getTimeSpent());
    }

    @Override
    public PlayerAction toTimeBreachedEvent(String player) {
        if (punishment instanceof LooseBreachPunishment) {
            return new TotalTimeoutSurrenderAction(player);
        } else {
            return new PlayerDefaultAction(player);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotalTimeRule that = (TotalTimeRule) o;

        if (limit != that.limit) return false;
        if (!punishment.equals(that.punishment)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (limit ^ (limit >>> 32));
        result = 31 * result + punishment.hashCode();
        return result;
    }

}
