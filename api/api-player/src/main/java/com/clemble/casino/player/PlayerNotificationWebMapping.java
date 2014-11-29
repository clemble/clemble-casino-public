package com.clemble.casino.player;

/**
 * Created by mavarazy on 11/29/14.
 */
public class PlayerNotificationWebMapping {

    final private static String NOTIFICATION_URL = "http://{host}/notification";

    final public static String MY_NOTIFICATIONS = "/my";

    public static String toNotificationUrl(String path) {
        return NOTIFICATION_URL + path;
    }
}
