package com.clemble.casino.goal.rule.time;

import com.clemble.casino.goal.rule.GoalRule;
import com.clemble.casino.rule.breach.BreachPunishment;
import com.clemble.casino.rule.breach.BreachPunishmentAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalTimeRule implements GoalRule, BreachPunishmentAware {

    final private int timeInDays;
    final private BreachPunishment punishment;

    @JsonCreator
    public GoalTimeRule(@JsonProperty("timeInDays") int timeInDays, @JsonProperty("punishment") BreachPunishment punishment) {
        this.timeInDays = timeInDays;
        this.punishment = punishment;
    }

    public int getTimeInDays() {
        return timeInDays;
    }

    @Override
    public BreachPunishment getPunishment() {
        return punishment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalTimeRule that = (GoalTimeRule) o;

        if (timeInDays != that.timeInDays) return false;
        if (!punishment.equals(that.punishment)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeInDays;
        result = 31 * result + punishment.hashCode();
        return result;
    }
}
