package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.validation.constraints.Min;

/**
 * Created by mavarazy on 1/4/15.
 */
public class TotalTimeoutCalculatorByEOD implements TotalTimeoutCalculator {

    @Min(0)
    final private int days;

    @JsonCreator
    public TotalTimeoutCalculatorByEOD(@JsonProperty("days") int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    @Override
    public DateTime calculate(DateTime startDate) {
        DateTime breachTime = startDate.plusDays(days).withTime(23, 59, 59, 00);
        // This is the end of today, plus days should be next day
        return breachTime.isAfterNow()
                ? breachTime // If breach time is after now, it's valid
                : breachTime.plusDays(1); // Breach time can't be in the past
    }

    @Override
    public DateTime calculate(GoalTimeSpanAware timeframe) {
        return calculate(timeframe.getStartDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotalTimeoutCalculatorByEOD that = (TotalTimeoutCalculatorByEOD) o;

        if (days != that.days) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return days;
    }

}
