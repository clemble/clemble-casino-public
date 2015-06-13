package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

/**
 * Created by mavarazy on 1/4/15.
 */
public class TotalTimeoutCalculatorByLimit implements TotalTimeoutCalculator {

    final private long limit;

    @JsonCreator
    public TotalTimeoutCalculatorByLimit(@JsonProperty("limit") long limit) {
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }

    @Override
    public DateTime calculate(DateTime startDate) {
        return startDate.plusMillis((int) limit);
    }

    @Override
    public DateTime calculate(GoalTimeSpanAware timeframe) {
        return calculate(timeframe.getStartDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotalTimeoutCalculatorByLimit that = (TotalTimeoutCalculatorByLimit) o;

        return limit == that.limit;

    }

    @Override
    public int hashCode() {
        return (int) (limit ^ (limit >>> 32));
    }

}
