package com.clemble.casino.goal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/17/14.
 */
public class GoalJudgeDuty implements GoalAware {

    final private GoalKey goalKey;

    @JsonCreator
    public GoalJudgeDuty(@JsonProperty(GOAL_KEY) GoalKey goalKey) {
        this.goalKey = goalKey;
    }

    @Override
    public GoalKey getGoalKey() {
        return goalKey;
    }
}
