package com.clemble.casino.tag;

/**
 * Created by mavarazy on 2/3/15.
 */
public class TagUtils {

    public static String getTag(String text) {
        String tag = null;
        int tagIndex = text.indexOf("#");
        if(tagIndex != -1) {
            tag = text.substring(tagIndex + 1, text.indexOf(" ", tagIndex));
        }
        return tag;
    }

}
