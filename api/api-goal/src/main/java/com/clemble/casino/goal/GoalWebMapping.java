package com.clemble.casino.goal;

import com.clemble.casino.web.mapping.WebMapping;

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

    final public static String GOAL_CONFIGURATIONS_URL = "http://{host}/goal/configuration";
    final public static String MY_CONFIGURATIONS = "/my";

    public static String toGoalConfigurationUrl(String path) {
        return GOAL_CONFIGURATIONS_URL + path;
    }
}
