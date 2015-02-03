package com.clemble.casino.tag;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 2/3/15.
 */
public class TagWebMapping implements WebMapping {

    final private static String TAG_URL = "http://{host}/tag";

    final public static String MY_TAGS = "/my";
    final public static String GET_TAGS = "/{player}";

    public static String toTagUrl(String path) {
        return TAG_URL + path;
    }

}
