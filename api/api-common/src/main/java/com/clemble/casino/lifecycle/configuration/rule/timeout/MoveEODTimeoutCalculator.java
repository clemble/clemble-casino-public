package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.validation.constraints.Min;

/**
 * Created by mavarazy on 5/11/15.
 */
public class MoveEODTimeoutCalculator implements MoveTimeoutCalculator {

    @Min(0)
    final private int days;

    @JsonCreator
    public MoveEODTimeoutCalculator(@JsonProperty("days") int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    @Override
    public long calculate(String timezone, long moveStart, long timeSpent) {
        DateTime breachTime = new DateTime(moveStart, DateTimeZone.forID(timezone)).
                withTime(23, 59, 59, 00). // EOD of move start
                plusDays(days);
        // This is the end of today, plus days should be next day
        return breachTime.isAfterNow()
                ? breachTime.getMillis() // If breach time is after now, it's valid
                : breachTime.plusDays(1).getMillis(); // Breach time can't be in the past
    }

    @Override
    public DateTime calculate(GoalTimeframeAware timeframe) {
        return timeframe.getLastUpdated().plusDays(days).withTime(23, 59, 59, 00);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoveEODTimeoutCalculator that = (MoveEODTimeoutCalculator) o;

        if (days != that.days) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return days;
    }

}
