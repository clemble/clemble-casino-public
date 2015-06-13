package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

/**
 * Created by mavarazy on 5/11/15.
 */
public class MoveTimeoutCalculatorByLimit implements MoveTimeoutCalculator {

    final private long limit;

    @JsonCreator
    public MoveTimeoutCalculatorByLimit(@JsonProperty("limit") long limit) {
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }

    @Override
    public DateTime calculate(DateTime lastUpdate) {
        return lastUpdate.plusMillis((int) limit);
    }

    @Override
    public DateTime calculate(GoalTimeSpanAware timeSpanAware) {
        return calculate(timeSpanAware.getLastUpdated());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoveTimeoutCalculatorByLimit that = (MoveTimeoutCalculatorByLimit) o;

        return limit == that.limit;

    }

    @Override
    public int hashCode() {
        return (int) (limit ^ (limit >>> 32));
    }

}
