package com.clemble.casino.goal;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 8/2/14.
 */
public abstract class GoalWebMapping implements WebMapping {

    final private static String GOAL_CONFIGURATIONS_URL = "http://{host}/configuration";
    final public static String MY_CONFIGURATIONS = "/my";
    final public static String MY_CONFIGURATIONS_CHOICES = "/my/choice";
    final public static String MY_CONFIGURATIONS_INTERVAL = "/my/interval";

    public static String toGoalConfigurationUrl(String path) {
        return GOAL_CONFIGURATIONS_URL + path;
    }

    final private static String GOAL_CONSTRUCTION_URL = "http://{host}/construction";

    final public static String GOAL_CONSTRUCTION = "/construction";
    final public static String MY_GOAL_CONSTRUCTION_PENDING = "/player/construction/my";

    final public static String GOAL_INITIATION = "/initiation/{goalKey}";
    final public static String GOAL_INITIATION_CONFIRM = "/initiation/{goalKey}/confirm";

    final public static String MY_GOAL_INITIATION = "/player/initiation/my";

    public static String toGoalConstructionUrl(String path) {
        return GOAL_CONSTRUCTION_URL + path;
    }

    final private static String GOAL_MANAGEMENT_URL = "http://{host}/management";

    final public static String GOAL_RECORD = "/record/{goalKey}";

    final public static String GOAL_STATE = "/active/{goalKey}";
    final public static String GOAL_STATE_ACTION = "/active/{goalKey}/action";

    final public static String MY_RECORDS = "/player/record/my";
    final public static String MY_RECORDS_STATE = "/player/record/my/{state}";

    final public static String MY_ACTIVE_GOALS = "/player/active/my";
    final public static String PLAYER_ACTIVE_GOALS = "/player/active/{player}";

    public static String toGoalManagementUrl(String path) {
        return GOAL_MANAGEMENT_URL + path;
    }

    final private static String GOAL_SUGGESTION_URL = "http://{host}/suggestion";

    final public static String MY_SUGGESTIONS = "/player/my";
    final public static String PLAYER_SUGGESTIONS = "/player/{player}";
    final public static String MY_SUGGESTIONS_GOAL = "/player/my/{goalKey}";

    final public static String SUGGESTION = "/repository/{goalKey}";

    public static String toGoalSuggestionUrl(String path) {
        return GOAL_SUGGESTION_URL + path;
    }

}
