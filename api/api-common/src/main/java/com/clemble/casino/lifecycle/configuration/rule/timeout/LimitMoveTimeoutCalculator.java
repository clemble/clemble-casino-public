package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

/**
 * Created by mavarazy on 5/11/15.
 */
public class LimitMoveTimeoutCalculator implements MoveTimeoutCalculator {

    final private long limit;

    @JsonCreator
    public LimitMoveTimeoutCalculator(@JsonProperty("limit") long limit) {
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }

    @Override
    public long calculate(String timezone, long moveStart, long timeSpent) {
        return System.currentTimeMillis() + (limit - (System.currentTimeMillis() - moveStart));
    }

    @Override
    public DateTime calculate(GoalTimeframeAware timeframe) {
        return timeframe.getLastUpdated().plusMillis((int) limit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LimitMoveTimeoutCalculator that = (LimitMoveTimeoutCalculator) o;

        if (limit != that.limit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (limit ^ (limit >>> 32));
    }

}
