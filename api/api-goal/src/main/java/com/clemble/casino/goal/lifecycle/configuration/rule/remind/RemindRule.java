package com.clemble.casino.goal.lifecycle.configuration.rule.remind;

import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 12/10/14.
 */
public class RemindRule implements GoalRule {

    final private long emailReminder;

    final private long phoneReminder;

    @JsonCreator
    public RemindRule(@JsonProperty("emailReminder") long emailReminder, @JsonProperty("phoneReminder") long phoneReminder) {
        this.emailReminder = emailReminder;
        this.phoneReminder = phoneReminder;
    }

    public long getEmailReminder() {
        return emailReminder;
    }

    public long getPhoneReminder() {
        return phoneReminder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemindRule that = (RemindRule) o;

        if (emailReminder != that.emailReminder) return false;
        if (phoneReminder != that.phoneReminder) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (emailReminder ^ (emailReminder >>> 32));
        result = 31 * result + (int) (phoneReminder ^ (phoneReminder >>> 32));
        return result;
    }

}
