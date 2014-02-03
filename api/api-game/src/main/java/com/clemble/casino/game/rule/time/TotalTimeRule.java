package com.clemble.casino.game.rule.time;

import java.util.Date;

import com.clemble.casino.game.GamePlayerClock;
import com.clemble.casino.game.action.DefaultGameAction;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.action.surrender.TotalTimeoutSurrenderAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("totalTime")
public class TotalTimeRule implements TimeRule {

    /**
     * Generated 09/04/13
     */
    private static final long serialVersionUID = 7452918511506230595L;

    final private long limit;
    final private TimeBreachPunishment punishment;

    @JsonCreator
    public TotalTimeRule(@JsonProperty("limit") long limit, @JsonProperty("punishment") TimeBreachPunishment punishment) {
        this.limit = limit;
        this.punishment = punishment;
    }

    public TimeBreachPunishment getPunishment() {
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
    public long timeUntilBreach(GamePlayerClock clock) {
        return clock.getMoveStart() == 0 ? Long.MAX_VALUE : timeUntilBreach((System.currentTimeMillis() - clock.getMoveStart()) + clock.getTimeSpent());
    }

    @Override
    public Date breachDate(GamePlayerClock clock) {
        return clock.getMoveStart() == 0 ? new Date(Long.MAX_VALUE) : new Date(clock.getMoveStart() + limit - clock.getTimeSpent());
    }

    @Override
    public GameAction toTimeBreachedEvent(String player) {
        switch (punishment) {
            case loose:
                return new TotalTimeoutSurrenderAction(player);
            case minimal:
            default:
                return new DefaultGameAction(player);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (limit ^ (limit >>> 32));
        result = prime * result + ((punishment == null) ? 0 : punishment.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TotalTimeRule other = (TotalTimeRule) obj;
        if (limit != other.limit)
            return false;
        if (punishment != other.punishment)
            return false;
        return true;
    }

}