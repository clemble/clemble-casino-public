package com.clemble.casino.goal.lifecycle.construction.event;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalConstructionCompleteEvent.JSON_TYPE)
public class GoalConstructionCompleteEvent implements GoalConstructionEvent {

    final public static String JSON_TYPE = "goal:construction:complete";

    final private String goalKey;

    public GoalConstructionCompleteEvent(String goalKey) {
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

        GoalConstructionCompleteEvent that = (GoalConstructionCompleteEvent) o;

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
