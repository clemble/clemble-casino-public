package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.validation.constraints.Min;

/**
 * Created by mavarazy on 5/11/15.
 */
public class MoveTimeoutCalculatorByEOD implements MoveTimeoutCalculator {

    @Min(0)
    final private int days;

    @JsonCreator
    public MoveTimeoutCalculatorByEOD(@JsonProperty("days") int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    @Override
    public DateTime calculate(DateTime lastUpdate) {
        return lastUpdate.plusDays(days).withTime(23, 59, 59, 00);
    }

    @Override
    public DateTime calculate(GoalTimeSpanAware timeSpanAware) {
        return calculate(timeSpanAware.getLastUpdated());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoveTimeoutCalculatorByEOD that = (MoveTimeoutCalculatorByEOD) o;

        return days == that.days;

    }

    @Override
    public int hashCode() {
        return days;
    }

}
