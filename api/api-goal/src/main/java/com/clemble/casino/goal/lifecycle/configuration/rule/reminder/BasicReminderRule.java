package com.clemble.casino.goal.lifecycle.configuration.rule.reminder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Basic;

/**
 * Created by mavarazy on 12/16/14.
 */
public class BasicReminderRule implements ReminderRule {

    final private long reminder;

    @JsonCreator
    public BasicReminderRule(@JsonProperty("reminder") long reminder) {
        this.reminder = reminder;
    }

    public long getReminder() {
        return reminder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicReminderRule that = (BasicReminderRule) o;

        if (reminder != that.reminder) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (reminder ^ (reminder >>> 32));
    }

}
