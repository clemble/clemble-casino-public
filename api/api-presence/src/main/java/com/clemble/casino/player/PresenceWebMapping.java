package com.clemble.casino.player;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 10/8/14.
 */
public abstract class PresenceWebMapping implements WebMapping {

    final private static String PRESENCE_URL = "http://{host}/presence";

    final public static String PLAYER_PRESENCES = "/";
    final public static String PLAYER_PRESENCE = "/{player}";
    final public static String MY_PRESENCE = "/my";

    final public static String PRESENCE_SESSIONS_PLAYER = "/my/session";
    final public static String PRESENCE_SESSIONS_PLAYER_SESSION = "/my/session/{sessionId}";

    public static String toPresenceUrl(String host, String path) {
        return PRESENCE_URL.replace("{host}", host) + path;
    }

    final public static String PLAYER_PRESENCES_PARAM = "players";
}
