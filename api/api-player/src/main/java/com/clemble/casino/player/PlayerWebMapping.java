package com.clemble.casino.player;

import com.clemble.casino.WebMapping;

public abstract class PlayerWebMapping implements WebMapping {

    final private static String PROFILE_URL = "http://{host}/player/profile";

    final public static String PLAYER_PROFILES = "/";
    final public static String PLAYER_PROFILE = "/{player}";
    final public static String MY_PROFILE = "/my";

    final public static String PLAYER_IMAGE = "/{player}/image";
    final public static String PLAYER_IMAGE_SMALL = "/{player}/image/small";
    final public static String MY_IMAGE = "/my/image";
    final public static String MY_IMAGE_SMALL = "/my/image/small";
    public static String toProfileUrl(String path) {
        return PROFILE_URL + path;
    }


    final private static String CONNECTION_URL = "http://{host}/player/connection";
    final public static String PLAYER_CONNECTIONS = "/{player}";
    final public static String PLAYER_OWNED_CONNECTIONS = "/{player}/owned";
    final public static String PLAYER_CONNECTION_CONNECTIONS = "/{player}/connected";
    final public static String MY_CONNECTIONS = "/my";
    final public static String MY_OWNED_CONNECTIONS = "/my/owned";
    final public static String MY_CONNECTED_CONNECTIONS = "/my/connected";
    public static String toConnectionUrl(String path) {
        return CONNECTION_URL + path;
    }

    final private static String SOCIAL_URL = "http://{host}/player/social";
    final public static String SOCIAL_PLAYER = "/{player}";
    final public static String SOCIAL_REGISTRATION_DESCRIPTION = "/registration/social";
    final public static String SOCIAL_REGISTRATION_GRANT = "/registration/grant";
    public static String toSocialUrl(String host, String path) {
        return SOCIAL_URL.replace("{host}", host) + path;
    }

}
