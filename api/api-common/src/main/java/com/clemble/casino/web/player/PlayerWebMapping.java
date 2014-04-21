package com.clemble.casino.web.player;

import com.clemble.casino.web.mapping.WebMapping;

public interface PlayerWebMapping extends WebMapping {

    final public static String REGISTRATION = "/player/";
    final public static String REGISTRATION_SOCIAL = "/social/";
    final public static String REGISTRATION_GRANT = "/grant/";

    final public static String PROFILES = "/player";
    final public static String PROFILES_PLAYER = "/player/{player}";

    final public static String CONNECTIONS_PLAYER = "/player/{player}/connections";

    final public static String SOCIAL_PLAYER = "/player/{player}/social";
    final public static String SOCIAL_PLAYER_CONNECTION = "/player/{player}/social/{connection}/{key}";

    final public static String PRESENCES = "/presence";
    final public static String PRESENCES_PLAYER = "/presence/{player}";

    final public static String PLAYER_PRESENCES_PARAM = "players";
    final public static String PLAYER_NOTIFICATION_DOMAIN_PATTERN = "%s_notif.%s.%s";
}
