package com.clemble.casino.game;

import com.clemble.casino.web.mapping.WebMapping;

abstract public class GameWebMapping implements WebMapping {

    final public static String TABLE_CHANNEL_POSTFIX = ".table";

    final public static String GAME_URL = "http://{host}/game";

    final public static String GAME_CONFIGURATION_URL = "http://{host}/game/configuration";
    final public static String CONFIGURATION = "/";

    final public static String CONSTRUCTION_AUTO = "/construction/auto";
    final public static String CONSTRUCTION_AVAILABILITY = "/construction/availability";
    final public static String CONSTRUCTION_AVAILABILITY_PENDING = "/construction/availability/{playerId}";

    final public static String GAME_CONSTRUCTION = "/construction/{session}";
    final public static String CONSTRUCTION_RESPONSES = "/construction/availability/reply";
    final public static String CONSTRUCTION_RESPONSES_PLAYER = "/construction/availability/reply/{session}/{playerId}";

    final public static String SESSIONS_RECORD = "/{session}";

    final public static String SESSIONS_STATE = "/{session}/state";
    final public static String SESSIONS_CONTEXT = "/{session}/context";
    final public static String SESSIONS_ACTIONS = "/{session}/action";

    final public static String INITIATION_READY = "/{session}/initiation/ready/{playerId}";

    public static String toGameConfigurationUrl(String path){
        return GAME_CONFIGURATION_URL + path;
    }

    public static String toGameUrl(String path) {
        return GAME_URL + path;
    }

    public static String toTable(String sessionKey) {
        // TODO have a statement for table
        return sessionKey + TABLE_CHANNEL_POSTFIX;
    }
}
