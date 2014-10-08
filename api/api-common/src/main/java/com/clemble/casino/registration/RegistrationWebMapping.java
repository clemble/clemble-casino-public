package com.clemble.casino.registration;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 10/8/14.
 */
public abstract class RegistrationWebMapping implements WebMapping {

    final public static String REGISTRATION_URL = "http://{host}/player/registration/";

    final public static String REGISTRATION_LOGIN = "/login";

    final public static String REGISTRATION_PROFILE = "/signin";

    final public static String REGISTRATION_BASIC_LOGIN = "/base/login";

    final public static String REGISTRATION_BASIC_PROFILE = "/base/signin";

    public static String toRegistrationUrl(String host, String path) {
        return REGISTRATION_URL.replace("{host}", host) + path;
    }

}
