package com.clemble.casino.player;

/**
 * Created by mavarazy on 11/11/14.
 */
public class PlayerConnectionWebMapping {

    final private static String CONNECTION_URL = "http://{host}/connection";

    final public static String PLAYER_CONNECTIONS = "/{player}";
    final public static String PLAYER_CONNECTION_CONNECTIONS = "/{player}/connected";
    final public static String PLAYER_OWNED_CONNECTIONS = "/{player}/owned";

    final public static String MY_CONNECTIONS = "/my";
    final public static String MY_OWNED_CONNECTIONS = "/my/owned";
    final public static String MY_CONNECTED_CONNECTIONS = "/my/connected";


    public static String toConnectionUrl(String path) {
        return CONNECTION_URL + path;
    }
}
