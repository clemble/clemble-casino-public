package com.clemble.casino.goal.lifecycle.management.event;

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

    @JsonCreator
    public GoalStartedEvent(@JsonProperty(GOAL_KEY) String goalKey) {
        this.goalKey = goalKey;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalStartedEvent that = (GoalStartedEvent) o;

        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return goalKey != null ? goalKey.hashCode() : 0;
    }

    @Override
    public String toString() {
        return goalKey + " > " + JSON_TYPE;
    }

}
