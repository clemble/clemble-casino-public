package com.clemble.casino.goal;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 8/2/14.
 */
public abstract class GoalWebMapping implements WebMapping {

    final private static String GOAL_URL = "http://{host}/goal/track";
    final public static String PLAYER_GOALS = "/{player}";
    final public static String PLAYER_GOALS_GOAL = "/{player}/{id}";
    final public static String PLAYER_GOALS_PENDING = "/{player}/pending";
    final public static String PLAYER_GOALS_REACHED = "/{player}/reached";
    final public static String PLAYER_GOALS_MISSED = "/{player}/missed";

    final public static String MY_GOALS = "/my";
    final public static String MY_GOALS_GOAL = "/my/{id}";
    final public static String MY_GOALS_GOAL_STATUS = "/my/{id}/status";
    final public static String MY_GOALS_PENDING = "/my/pending";
    final public static String MY_GOALS_REACHED = "/my/reached";
    final public static String MY_GOALS_MISSED = "/my/missed";

    public static String toGoalUrl(String path) {
        return GOAL_URL + path;
    }

    final private static String GOAL_CONFIGURATIONS_URL = "http://{host}/goal/configuration";
    final public static String MY_CONFIGURATIONS = "/my";

    public static String toGoalConfigurationUrl(String path) {
        return GOAL_CONFIGURATIONS_URL + path;
    }

    final private static String GOAL_CONSTRUCTION_URL = "http://{host}/goal/construction";
    final public static String GOAL_CONSTRUCTION = "/";
    final public static String GOAL_CONSTRUCTION_PENDING = "/my/pending";

    final public static String GOAL_INITIATION_PENDING = "/initiation/my";

    public static String toGoalConstructionUrl(String path) {
        return GOAL_CONSTRUCTION_URL + path;
    }

    final private static String GOAL_MANAGEMENT_URL = "http://{host}/goal/management";

    final public static String MY_RECORDS = "/record/my";
    final public static String GOAL_RECORD = "/record/{goalKey}";

    final public static String GOAL_STATE = "/state/{goalKey}/context";
    final public static String GOAL_ACTIONS = "/state/{goalKey}/action";

    public static String toGoalManagementUrl(String path) {
        return GOAL_MANAGEMENT_URL + path;
    }

}
