package com.clemble.casino.log;

import com.clemble.casino.WebMapping;

/**
 * Created by mavarazy on 5/15/15.
 */
public class LogWebMapping implements WebMapping {

    final private static String LOG_URL = "http://{host}/log";

    final public static String LOG_ERROR = "/error";

    public static String toLogUrl(String path) {
        return LOG_URL + path;
    }

}
