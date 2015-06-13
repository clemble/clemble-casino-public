package com.clemble.casino;

import org.joda.time.DateTimeZone;

/**
 * Created by mavarazy on 4/27/15.
 */
public interface TimeZoneAware {

    String TIME_ZONE = "timezone";

    DateTimeZone getTimezone();

}
