package com.clemble.casino.player;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 12/8/14.
 */
public class PlayerPhoneWebMapping implements WebMapping {

    final private static String PHONE_URL = "http://{host}/phone";

    final public static String MY = "/my";
    final public static String VERIFY = "/{verificationCode}";

    public static String toPhoneUrl(String path) {
        return PHONE_URL + path;
    }

}
