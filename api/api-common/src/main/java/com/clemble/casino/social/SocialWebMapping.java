package com.clemble.casino.social;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 10/8/14.
 */
public abstract class SocialWebMapping implements WebMapping {

    final private static String SOCIAL_URL = "http://{host}/social";

    final public static String SOCIAL_PLAYER = "/{player}";
    final public static String SOCIAL_REGISTRATION_DESCRIPTION = "/registration/social";
    final public static String SOCIAL_REGISTRATION_GRANT = "/registration/grant";

    public static String toSocialUrl(String host, String path) {
        return SOCIAL_URL.replace("{host}", host) + path;
    }

}
