package com.clemble.casino.lifecycle.configuration.rule.time;

import com.clemble.casino.lifecycle.configuration.rule.breach.BreachPunishment;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("rule:time:total")
public class TotalTimeRule implements TimeRule {

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
    public long timeUntilBreach(PlayerClock clock) {
        return clock.getMoveStart() == 0 ? Long.MAX_VALUE : limit - ((System.currentTimeMillis() - clock.getMoveStart()) + clock.getTimeSpent());
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
