package com.clemble.casino.goal.rule.status;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalStatusRule {

    final private StatusBreachPunishment punishment;
    final private StatusUpdateFrequency updateFrequency;

    @JsonCreator
    public GoalStatusRule(@JsonProperty("punishment") StatusBreachPunishment punishment, @JsonProperty("updateFrequency")  StatusUpdateFrequency updateFrequency) {
        this.punishment = punishment;
        this.updateFrequency = updateFrequency;
    }

    public StatusBreachPunishment getPunishment() {
        return punishment;
    }

    public StatusUpdateFrequency getUpdateFrequency() {
        return updateFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalStatusRule that = (GoalStatusRule) o;

        if (!punishment.equals(that.punishment)) return false;
        if (updateFrequency != that.updateFrequency) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = punishment.hashCode();
        result = 31 * result + updateFrequency.hashCode();
        return result;
    }
}
