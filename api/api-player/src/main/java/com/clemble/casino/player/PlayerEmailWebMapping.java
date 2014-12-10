package com.clemble.casino.player;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 12/8/14.
 */
public class PlayerEmailWebMapping implements WebMapping {

    final private static String EMAIL_URL = "http://{host}/email";

    final public static String VERIFY = "/verify";

    public static String toEmailUrl(String path) {
        return EMAIL_URL + path;
    }

}
