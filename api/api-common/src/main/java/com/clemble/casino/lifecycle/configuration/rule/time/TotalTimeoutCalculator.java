package com.clemble.casino.lifecycle.configuration.rule.time;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 1/4/15.
 */
public class TotalTimeoutCalculator implements TimeoutCalculator {

    final private long limit;

    @JsonCreator
    public TotalTimeoutCalculator(@JsonProperty("limit") long limit) {
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }

    @Override
    public long calculate(PlayerClock clock) {
        return clock.getMoveStart() == 0 ? Long.MAX_VALUE : limit - ((System.currentTimeMillis() - clock.getMoveStart()) + clock.getTimeSpent());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotalTimeoutCalculator that = (TotalTimeoutCalculator) o;

        if (limit != that.limit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (limit ^ (limit >>> 32));
    }

}
