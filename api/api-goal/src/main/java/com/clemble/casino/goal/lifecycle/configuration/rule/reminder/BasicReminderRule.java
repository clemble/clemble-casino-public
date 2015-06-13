package com.clemble.casino.goal.lifecycle.configuration.rule.reminder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Basic;

/**
 * Created by mavarazy on 12/16/14.
 */
@JsonTypeName("rule:reminder:basic")
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

        return reminder == that.reminder;

    }

    @Override
    public String toString() {
        return "basicReminderRule:" + reminder;
    }

    @Override
    public int hashCode() {
        return (int) (reminder ^ (reminder >>> 32));
    }

}
