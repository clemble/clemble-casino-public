package com.clemble.casino.goal.rule.time;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalTimeRule {

    final private int timeInDays;
    final private GoalTimeBreachPunishment punishment;

    @JsonCreator
    public GoalTimeRule(@JsonProperty("timeInDays") int timeInDays, @JsonProperty("punishment") GoalTimeBreachPunishment punishment) {
        this.timeInDays = timeInDays;
        this.punishment = punishment;
    }

    public int getTimeInDays() {
        return timeInDays;
    }

    public GoalTimeBreachPunishment getPunishment() {
        return punishment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalTimeRule that = (GoalTimeRule) o;

        if (timeInDays != that.timeInDays) return false;
        if (punishment != that.punishment) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeInDays;
        result = 31 * result + punishment.hashCode();
        return result;
    }

}
