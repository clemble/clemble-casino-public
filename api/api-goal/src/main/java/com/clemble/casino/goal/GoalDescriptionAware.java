package com.clemble.casino.goal;

import org.joda.time.DateTimeZone;

/**
 * Created by mavarazy on 8/16/14.
 */
public interface GoalDescriptionAware {

    public String getGoal();

    public DateTimeZone getTimezone();

}
