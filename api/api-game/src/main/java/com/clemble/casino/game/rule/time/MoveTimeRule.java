package com.clemble.casino.game.rule.time;

import java.util.Date;

import com.clemble.casino.game.GamePlayerClock;
import com.clemble.casino.game.action.DefaultGameAction;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.action.surrender.MoveTimeoutSurrenderAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("moveTime")
public class MoveTimeRule implements TimeRule {

    /**
     * Generated 09/04/13
     */
    private static final long serialVersionUID = -2949008185370674021L;

    final private TimeBreachPunishment punishment;
    final private long limit;

    @JsonCreator
    public MoveTimeRule(@JsonProperty("limit") long limit, @JsonProperty("punishment") TimeBreachPunishment punishment) {
        this.limit = limit;
        this.punishment = punishment;
    }

    public TimeBreachPunishment getPunishment() {
        return punishment;
    }

    @Override
    public long timeUntilBreach(long totalTimeSpent) {
        return limit - totalTimeSpent;
    }

    @Override
    public long timeUntilBreach(GamePlayerClock clock) {
        return clock.getMoveStart() == 0 ? Long.MAX_VALUE : timeUntilBreach(System.currentTimeMillis() - clock.getMoveStart());
    }

    @Override
    public Date breachDate(GamePlayerClock clock) {
        return clock.getMoveStart() == 0 ? new Date(Long.MAX_VALUE) : new Date(clock.getMoveStart() + limit); 
    }

    @Override
    public GameAction toTimeBreachedEvent(String player) {
        switch (punishment) {
            case loose:
                return new MoveTimeoutSurrenderAction(player);
            case minimal:
            default:
                return new DefaultGameAction(player);
        }
    }

    @Override
    public long getLimit() {
        return limit;
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
        MoveTimeRule other = (MoveTimeRule) obj;
        if (limit != other.limit)
            return false;
        if (punishment != other.punishment)
            return false;
        return true;
    }

}
