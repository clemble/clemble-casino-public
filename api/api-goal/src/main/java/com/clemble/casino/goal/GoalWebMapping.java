package com.clemble.casino.goal;

import com.clemble.casino.web.mapping.WebMapping;

/**
 * Created by mavarazy on 8/2/14.
 */
public abstract class GoalWebMapping implements WebMapping {

    final private static String GOAL_URL = "http://{host}/goal";
    final public static String PLAYER_GOALS = "/{player}";
    final public static String PLAYER_GOALS_PENDING = "/{player}/pending";
    final public static String PLAYER_GOALS_REACHED = "/{player}/reached";
    final public static String PLAYER_GOALS_MISSED = "/{player}/missed";

    public static String toGoalUrl(String path) {
        return GOAL_URL + path;
    }

}
