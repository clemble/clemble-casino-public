package com.clemble.casino.player;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 11/11/14.
 */
public class PlayerConnectionWebMapping implements WebMapping {

    final private static String CONNECTION_URL = "http://{host}/connection";

    final public static String PLAYER_CONNECTIONS = "/{player}";
    final public static String MY_CONNECTIONS = "/my";

    final public static String MY_INVITATIONS = "/my/invitations";
    final public static String MY_INVITATIONS_REPLY = "/my/invitations/{player}";

    public static String toConnectionUrl(String path) {
        return CONNECTION_URL + path;
    }
}
