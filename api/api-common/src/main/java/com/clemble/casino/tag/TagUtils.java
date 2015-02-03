package com.clemble.casino.tag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mavarazy on 2/3/15.
 */
public class TagUtils {

    final private static Pattern pattern = Pattern.compile("#\\w+");///b#(.*)/b

    public static String getTag(String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
            return matcher.group().substring(1);
        else
            return null;
    }

}
