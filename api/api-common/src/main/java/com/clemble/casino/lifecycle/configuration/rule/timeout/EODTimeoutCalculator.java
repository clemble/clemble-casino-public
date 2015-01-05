package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.clemble.casino.lifecycle.configuration.rule.time.PlayerClock;
import com.clemble.casino.utils.ClembleDateUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

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
    public long calculate(PlayerClock clock) {
        if (clock.getMoveStart() == 0) {
            return Long.MAX_VALUE;
        } else {
            Date moveStart = new Date(clock.getMoveStart());
            return ClembleDateUtils.getEndOfDay(DateUtils.addDays(moveStart, days)).getTime();
        }
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
