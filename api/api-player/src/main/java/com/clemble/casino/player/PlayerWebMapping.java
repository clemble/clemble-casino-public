package com.clemble.casino.player;

import com.clemble.casino.WebMapping;

public abstract class PlayerWebMapping implements WebMapping {

    final private static String PROFILE_URL = "http://{host}/profile";

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

}
