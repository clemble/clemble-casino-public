package com.clemble.casino.search;

import com.clemble.casino.WebMapping;

/**
 * @author Anton Oparin (antono@clemble.com)
 */
public abstract class SearchWebMapping implements WebMapping {

    final private static String PLAYER_URL = "http://{host}/";
    final public static String SEARCH_PLAYER = "/search/player";

    public static String toSearchUrl(String path) {
        return PLAYER_URL + path;
    }

}
