package com.clemble.casino.goal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/17/14.
 */
public class GoalJudgeDuty implements GoalAware {

    final private String goalKey;

    @JsonCreator
    public GoalJudgeDuty(@JsonProperty(GOAL_KEY) String goalKey) {
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

        GoalJudgeDuty that = (GoalJudgeDuty) o;

        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return goalKey != null ? goalKey.hashCode() : 0;
    }
}
