package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

/**
 * Created by mavarazy on 1/4/15.
 */
public class EODTimeoutCalculator implements TimeoutCalculator {

    final private int days;

    @JsonCreator
    public EODTimeoutCalculator(@JsonProperty("days") int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    @Override
    public long calculate(long moveStart) {
        return calculate(moveStart, 0);
    }

    @Override
    public long calculate(long moveStart, long timeSpent) {
        return new DateTime(moveStart).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).plusDays(days).getMillis();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EODTimeoutCalculator that = (EODTimeoutCalculator) o;

        if (days != that.days) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return days;
    }

}
