package com.clemble.casino.goal.event.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 1/13/15.
 */
@JsonTypeName(GoalForbidBetAction.JSON_TYPE)
public class GoalForbidBetAction {

    final public static String JSON_TYPE = "goal:management:bid:forbid";

    final private static GoalForbidBetAction INSTANCE = new GoalForbidBetAction();

    public GoalForbidBetAction() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return JSON_TYPE ;
    }

    @JsonCreator
    public static GoalForbidBetAction create(@JsonProperty("type") String type) {
        return INSTANCE;
    }

}
