package com.clemble.casino.goal.lifecycle.initiation.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/16/14.
 */
@JsonTypeName(GoalInitiationCompleteEvent.JSON_TYPE)
public class GoalInitiationCompleteEvent implements GoalInitiationEvent {

    final public static String JSON_TYPE = "goal:initiation:created";

    final private String goalKey;

    @JsonCreator
    public GoalInitiationCompleteEvent(@JsonProperty("goalKey") String goalKey) {
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

        GoalInitiationCompleteEvent that = (GoalInitiationCompleteEvent) o;

        if (!goalKey.equals(that.goalKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return goalKey.hashCode();
    }

}
