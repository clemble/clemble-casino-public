package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalStartedEvent.JSON_TYPE)
public class GoalStartedEvent implements GoalManagementEvent {

    final public static String JSON_TYPE = "goal:management:started";

    final private String goalKey;
    final private GoalState state;

    @JsonCreator
    public GoalStartedEvent(
        @JsonProperty(GOAL_KEY) String goalKey,
        @JsonProperty("state") GoalState state) {
        this.goalKey = goalKey;
        this.state = state;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    public GoalState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalStartedEvent that = (GoalStartedEvent) o;

        if (!goalKey.equals(that.goalKey)) return false;
        if (!state.equals(that.state)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return goalKey + " > " + JSON_TYPE;
    }

}
