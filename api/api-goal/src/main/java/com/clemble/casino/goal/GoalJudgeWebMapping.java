package com.clemble.casino.goal;

/**
 * Created by mavarazy on 8/17/14.
 */

import com.clemble.casino.web.mapping.WebMapping;

/**
 * Created by mavarazy on 8/2/14.
 */
public abstract class GoalJudgeWebMapping implements WebMapping {

    final private static String GOAL_JUDGE_URL = "http://{host}//goal/judge";

    final public static String MY_DUTIES_AND_INVITATIONS = "/invitations/my/";
    final public static String MY_INVITATIONS = "/invitations/my/initiated";
    final public static String MY_DUTIES = "/invitations/my/duties";

    final public static String INVITATION_REPLY = "/invitations/{player}/{id}";

    public static String toGoalJudgeUrl(String path) {
        return GOAL_JUDGE_URL + path;
    }

}
