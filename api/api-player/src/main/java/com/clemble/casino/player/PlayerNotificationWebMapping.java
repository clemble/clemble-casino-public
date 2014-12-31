package com.clemble.casino.player;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 11/29/14.
 */
public class PlayerNotificationWebMapping implements WebMapping {

    final private static String NOTIFICATION_URL = "http://{host}/notification";

    final public static String MY_NOTIFICATIONS = "/my";
    final public static String MY_NOTIFICATIONS_DELETE = "/my/{key}";

    public static String toNotificationUrl(String path) {
        return NOTIFICATION_URL + path;
    }

}
