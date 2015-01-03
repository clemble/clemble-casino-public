package com.clemble.casino.goal;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 8/2/14.
 */
public abstract class GoalWebMapping implements WebMapping {

    final private static String GOAL_CONFIGURATIONS_URL = "http://{host}/configuration";
    final public static String MY_CONFIGURATIONS = "/my";

    public static String toGoalConfigurationUrl(String path) {
        return GOAL_CONFIGURATIONS_URL + path;
    }

    final private static String GOAL_CONSTRUCTION_URL = "http://{host}/construction";
    final public static String GOAL_CONSTRUCTION = "/";
    final public static String GOAL_CONSTRUCTION_PENDING = "/my/pending";

    final public static String GOAL_INITIATION = "/initiation/{goalKey}";
    final public static String GOAL_INITIATION_BID = "/initiation/{goalKey}/bid";
    final public static String MY_GOAL_INITIATION = "/initiation/my";
    final public static String GOAL_INITIATION_CONFIRM = "/initiation/{goalKey}/confirm";

    final public static String MY_FRIEND_INITIATIONS = "/initiation/timeline/my";
    final public static String MY_FRIEND_INITIATION = "/initiation/timeline/my/{goalKey}";
    final public static String PLAYER_FRIEND_INITIATIONS = "/initiation/timeline/{player}";
    final public static String PLAYER_FRIEND_INITIATION = "/initiation/timeline/{player}/{goalKey}";

    public static String toGoalConstructionUrl(String path) {
        return GOAL_CONSTRUCTION_URL + path;
    }

    final private static String GOAL_MANAGEMENT_URL = "http://{host}/management";

    final public static String MY_RECORDS = "/record/my";
    final public static String MY_RECORDS_STATE = "/record/my/{state}";
    final public static String GOAL_RECORD = "/record/{goalKey}";

    final public static String GOAL_STATE = "/active/my/{goalKey}/context";
    final public static String GOAL_ACTIONS = "/active/my/{goalKey}/action";

    final public static String PLAYER_ACTIVE_GOALS = "/active/{player}";
    final public static String MY_ACTIVE_GOALS = "/active/my";

    final public static String MY_FRIEND_GOALS = "/connections/timeline/my";
    final public static String MY_FRIEND_GOAL = "/connections/timeline/my/{goalKey}";
    final public static String PLAYER_FRIEND_GOALS = "/connections/timeline/{player}";
    final public static String PLAYER_FRIEND_GOAL = "/connections/timeline/{player}/{goalKey}";

    public static String toGoalManagementUrl(String path) {
        return GOAL_MANAGEMENT_URL + path;
    }

    final private static String GOAL_SUGGESTION_URL = "http://{host}/suggestion";

    final public static String MY_SUGGESTIONS = "/player/my";
    final public static String PLAYER_SUGGESTIONS = "/player/{player}";

    final public static String SUGGESTION = "/repository/{goalKey}";

    public static String toGoalSuggestionUrl(String path) {
        return GOAL_SUGGESTION_URL + path;
    }

}
