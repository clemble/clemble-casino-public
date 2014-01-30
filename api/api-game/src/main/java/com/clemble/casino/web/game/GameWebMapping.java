package com.clemble.casino.web.game;

import com.clemble.casino.web.mapping.WebMapping;

public interface GameWebMapping extends WebMapping {

    final public static String GAME_CONFIGURATIONS_MATCH = "/configuration/match";
    final public static String GAME_CONFIGURATIONS_POT = "/configuration/pot";
    final public static String GAME_CONFIGURATIONS_TOURNAMENT = "/configuration/tournament";

    final public static String GAME_CONSTRUCTION_AUTO = "/construction/auto";
    final public static String GAME_CONSTRUCTION_AVAILABILITY = "/construction/availability";
    final public static String GAME_CONSTRUCTION_AVAILABILITY_PENDING = "/construction/availability/{playerId}";

    final public static String GAME_CONSTRUCTION = "/constuction/{game}/{session}";
    final public static String GAME_CONSTRUCTION_RESPONSES = "/construction/availability/reply";
    final public static String GAME_CONSTRUCTION_RESPONSES_PLAYER = "/construction/availability/reply/{game}/{session}/{playerId}";

    final public static String GAME_SESSIONS_SESSION = "/{game}/{session}";

    final public static String GAME_INITIATION_READY = "/{game}/{session}/initiation/ready/{playerId}";

    final public static String GAME_SESSIONS_STATE = "/{game}/{session}/state";
    final public static String GAME_SESSIONS_ACTIONS = "/{game}/{session}/action";
    final public static String GAME_SESSIONS_ACTIONS_ACTION = "/{game}/{session}/action/{actionId}";

}
