package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.clemble.casino.lifecycle.configuration.rule.time.DeadlineAware;
import org.joda.time.DateTime;

/**
 * Created by mavarazy on 5/11/15.
 */
public interface GoalTimeSpanAware extends DeadlineAware {

    public DateTime getStartDate();

    public DateTime getLastUpdated();

    public DateTime getDeadline();

}
