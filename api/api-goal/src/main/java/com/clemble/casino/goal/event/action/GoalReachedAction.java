package com.clemble.casino.goal.event.action;

import com.clemble.casino.goal.GoalStatusAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/16/14.
 */
@JsonTypeName(GoalReachedAction.JSON_TYPE)
public class GoalReachedAction implements GoalAction, GoalStatusAware {

    final public static String JSON_TYPE = "goal:management:reached";

    final private String status;

    @JsonCreator
    public GoalReachedAction(@JsonProperty("status") String status) {
        this.status = status;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalReachedAction that = (GoalReachedAction) o;

        return !(status != null ? !status.equals(that.status) : that.status != null);

    }

    @Override
    public int hashCode() {
        return status != null ? status.hashCode() : 0;
    }

    @Override
    public String toString() {
        return JSON_TYPE + " > " + status;
    }

}
