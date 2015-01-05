package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.clemble.casino.lifecycle.configuration.rule.time.PlayerClock;
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
    public long calculate(long moveStart) {
        return calculate(moveStart, 0);
    }

    @Override
    public long calculate(long moveStart, long timeSpent) {
        return  limit - ((System.currentTimeMillis() - moveStart) + timeSpent);
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
