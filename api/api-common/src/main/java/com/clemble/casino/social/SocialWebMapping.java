package com.clemble.casino.social;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 10/8/14.
 */
public abstract class SocialWebMapping implements WebMapping {

    final private static String SOCIAL_URL = "http://{host}/social";

    final public static String SOCIAL_REGISTRATION_BASE = "/social/registration";
    final public static String SOCIAL_SIGN_IN_BASE = "/social/signin";

    final public static String SOCIAL_PLAYER = "/{player}";
    final public static String SOCIAL_REGISTRATION_GRANT = "/registration/grant";
    final public static String SOCIAL_REGISTRATION_DESCRIPTION = "/registration/social";

    public static String toSocialUrl(String host, String path) {
        return SOCIAL_URL.replace("{host}", host) + path;
    }

}
