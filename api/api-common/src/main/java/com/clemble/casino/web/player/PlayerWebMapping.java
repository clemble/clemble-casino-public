package com.clemble.casino.web.player;

import com.clemble.casino.web.mapping.WebMapping;

public abstract class PlayerWebMapping implements WebMapping {

    final private static String PROFILE_URL = "http://{host}/player/profile";

    final public static String PLAYER_PROFILES = "/";
    final public static String PLAYER_PROFILE = "/{player}";
    final public static String MY_PROFILE = "/my";

    final public static String PLAYER_IMAGE = "/{player}/image";
    final public static String MY_IMAGE = "/my/image";
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


    final private static String PRESENCE_URL = "http://{host}/player/presence";

    final public static String PLAYER_PRESENCES = "/";
    final public static String PLAYER_PRESENCE = "/{player}";
    final public static String MY_PRESENCE = "/my";

    final public static String PRESENCE_SESSIONS_PLAYER = "/my/session";
    final public static String PRESENCE_SESSIONS_PLAYER_SESSION = "/my/session/{sessionId}";
    public static String toPresenceUrl(String host, String path) {
        return PRESENCE_URL.replace("{host}", host) + path;
    }

    final public static String PLAYER_PRESENCES_PARAM = "players";
    final public static String PLAYER_NOTIFICATION_DOMAIN_PATTERN = "%s_notif.%s.%s";


    final public static String REGISTRATION_URL = "http://{host}/player/registration/";
    final public static String REGISTRATION_LOGIN = "/login";
    final public static String REGISTRATION_PROFILE = "/signin";
    final public static String REGISTRATION_BASIC_LOGIN = "/base/login";
    final public static String REGISTRATION_BASIC_PROFILE = "/base/signin";
    public static String toRegistrationUrl(String host, String path) {
        return REGISTRATION_URL.replace("{host}", host) + path;
    }

}
