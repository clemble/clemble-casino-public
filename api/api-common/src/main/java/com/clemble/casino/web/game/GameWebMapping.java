package com.clemble.casino.web.game;

import com.clemble.casino.web.mapping.WebMapping;

abstract public class GameWebMapping implements WebMapping {

    final public static String GAME_URL = "http://{host}/game";

    final public static String CONFIGURATION = "/configurations";

    final public static String CONSTRUCTION_AUTO = "/construction/auto";
    final public static String CONSTRUCTION_AVAILABILITY = "/construction/availability";
    final public static String CONSTRUCTION_AVAILABILITY_PENDING = "/construction/availability/{playerId}";

    final public static String GAME_CONSTRUCTION = "/constuction/{game}/{session}";
    final public static String CONSTRUCTION_RESPONSES = "/construction/availability/reply";
    final public static String CONSTRUCTION_RESPONSES_PLAYER = "/construction/availability/reply/{game}/{session}/{playerId}";

    final public static String SESSIONS_RECORD = "/{game}/{session}";

    final public static String SESSIONS_STATE = "/{game}/{session}/state";
    final public static String SESSIONS_CONTEXT = "/{game}/{session}/context";
    final public static String SESSIONS_ACTIONS = "/{game}/{session}/action";

    final public static String INITIATION_READY = "/{game}/{session}/initiation/ready/{playerId}";

    public static String toGameUrl(String path) {
        return GAME_URL + path;
    }
}
