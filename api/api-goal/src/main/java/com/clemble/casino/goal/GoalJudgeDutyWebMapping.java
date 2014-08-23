package com.clemble.casino.goal;

/**
 * Created by mavarazy on 8/23/14.
 */
public class GoalJudgeDutyWebMapping {

    final private static String GOAL_JUDGE_DUTY_URL = "http://{host}//goal/judge/duties";

    final public static String MY_DUTIES = "/my";

    public static String toGoalJudgeDutyUrl(String path) {
        return GOAL_JUDGE_DUTY_URL + path;
    }


}
