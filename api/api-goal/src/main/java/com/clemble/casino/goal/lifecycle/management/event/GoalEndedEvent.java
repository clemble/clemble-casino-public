package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.management.outcome.OutcomeAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalEndedEvent.JSON_TYPE)
public class GoalEndedEvent implements GoalManagementEvent, OutcomeAware {

    final public static String JSON_TYPE = "goal:management:end";

    final private String goalKey;
    final private Outcome outcome;

    @JsonCreator
    public GoalEndedEvent(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("outcome") Outcome outcome) {
        this.goalKey = goalKey;
        this.outcome = outcome;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalEndedEvent)) return false;

        GoalEndedEvent that = (GoalEndedEvent) o;

        if (!goalKey.equals(that.goalKey)) return false;
        if (!outcome.equals(that.outcome)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + outcome.hashCode();
        return result;
    }

}
