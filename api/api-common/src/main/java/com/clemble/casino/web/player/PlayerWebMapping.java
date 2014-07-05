package com.clemble.casino.web.player;

import com.clemble.casino.web.mapping.WebMapping;

public interface PlayerWebMapping extends WebMapping {

    String PROFILE_PREFIX = "/player/profile";
    String PROFILE = "/";
    String PROFILE_PLAYER = "/{player}";

    String CONNECTION_PREFIX = "/player/connections";
    String CONNECTION_PLAYER = "/{player}";

    String SOCIAL_PREFIX = "/player/social";
    String SOCIAL_PLAYER = "/{player}";
    String SOCIAL_REGISTRATION_DESCRIPTION = "/registration/social";
    String SOCIAL_REGISTRATION_GRANT = "/registration/grant";

    String PRESENCE_PREFIX = "/player/presence";
    String PRESENCE = "/";
    String PRESENCE_PLAYER = "/{player}";
    String PRESENCE_SESSIONS_PLAYER = "/player/{playerId}/session";
    String PRESENCE_SESSIONS_PLAYER_SESSION = "/player/{playerId}/session/{sessionId}";

    String PLAYER_PRESENCES_PARAM = "players";
    String PLAYER_NOTIFICATION_DOMAIN_PATTERN = "%s_notif.%s.%s";

    String REGISTRATION_PREFIX = "/player/registration/";
    String REGISTRATION_LOGIN = "/login";
    String REGISTRATION_PROFILE = "/signin";

}
