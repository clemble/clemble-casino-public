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

    // TODO Consider leaving only pending invitations
    final public static String MY_INVITATIONS_PENDING = "/invitations/my/pending";
    final public static String MY_INVITATIONS_ACCEPTED = "/invitations/my/accepted";
    final public static String MY_INVITATIONS_DECLINED = "/invitations/my/declined";

    final public static String INVITATION_REPLY = "/invitations/my/pending/{goalKey}";

    public static String toGoalJudgeUrl(String path) {
        return GOAL_JUDGE_URL + path;
    }

}
