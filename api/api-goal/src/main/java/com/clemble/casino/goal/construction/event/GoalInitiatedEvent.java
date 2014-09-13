package com.clemble.casino.goal.construction.event;

import com.clemble.casino.goal.event.GoalEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 9/13/14.
 */
@JsonTypeName("goal:initiation:created")
public class GoalInitiatedEvent implements GoalEvent {

    final private String goalKey;

    @JsonCreator
    public GoalInitiatedEvent(@JsonProperty(GOAL_KEY) String goalKey) {
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

        GoalInitiatedEvent that = (GoalInitiatedEvent) o;

        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return goalKey != null ? goalKey.hashCode() : 0;
    }
}
