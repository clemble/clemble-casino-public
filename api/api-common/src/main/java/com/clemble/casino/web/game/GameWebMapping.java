package com.clemble.casino.web.game;

import com.clemble.casino.web.mapping.WebMapping;

public interface GameWebMapping extends WebMapping {

    final public static String GAME_CONFIGURATIONS = "/configurations";

    final public static String GAME_CONSTRUCTION_AUTO = "/construction/auto";
    final public static String GAME_CONSTRUCTION_AVAILABILITY = "/construction/availability";
    final public static String GAME_CONSTRUCTION_AVAILABILITY_PENDING = "/construction/availability/{playerId}";

    final public static String GAME_CONSTRUCTION = "/constuction/{game}/{session}";
    final public static String GAME_CONSTRUCTION_RESPONSES = "/construction/availability/reply";
    final public static String GAME_CONSTRUCTION_RESPONSES_PLAYER = "/construction/availability/reply/{game}/{session}/{playerId}";

    final public static String GAME_SESSIONS_RECORD = "/{game}/{session}";

    final public static String GAME_SESSIONS_STATE = "/{game}/{session}/state";
    final public static String GAME_SESSIONS_CONTEXT = "/{game}/{session}/context";
    final public static String GAME_SESSIONS_ACTIONS = "/{game}/{session}/action";
    final public static String GAME_SESSIONS_ACTIONS_ACTION = "/{game}/{session}/action/{actionId}";

    final public static String GAME_INITIATION_READY = "/{game}/{session}/initiation/ready/{playerId}";


}