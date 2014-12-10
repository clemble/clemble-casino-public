package com.clemble.casino.goal.lifecycle.configuration.rule.remind;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 12/10/14.
 */
public class EmailReminderRule implements RemindRule {

    final private long reminder;

    @JsonCreator
    public EmailReminderRule(@JsonProperty("reminder") long reminder) {
        this.reminder = reminder;
    }

    @Override
    public long getReminder() {
        return reminder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailReminderRule that = (EmailReminderRule) o;

        if (reminder != that.reminder) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (reminder ^ (reminder >>> 32));
    }

}
