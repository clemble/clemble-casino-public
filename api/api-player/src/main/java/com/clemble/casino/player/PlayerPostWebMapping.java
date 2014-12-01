package com.clemble.casino.player;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 11/30/14.
 */
public class PlayerPostWebMapping implements WebMapping {

    final private static String POST_URL = "http://{host}/feed";

    final public static String MY_POSTS = "/my";

    public static String toPostUrl(String path) {
        return POST_URL + path;
    }

}
