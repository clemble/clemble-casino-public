package com.clemble.casino.goal;

/**
 * Created by mavarazy on 8/17/14.
 */
public class GoalJudgeDuty implements GoalAware {

    final private GoalKey goalKey;

    public GoalJudgeDuty(GoalKey goalKey) {
        this.goalKey = goalKey;
    }

    @Override
    public GoalKey getGoalKey() {
        return goalKey;
    }
}
