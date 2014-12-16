package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.ReminderRule;
import com.clemble.casino.goal.lifecycle.management.GoalRole;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 12/16/14.
 */
public class GoalRoleConfiguration {

    final private GoalRole role;
    final private ReminderRule emailReminderRule;
    final private ReminderRule phoneReminderRule;

    @JsonCreator
    public GoalRoleConfiguration(
        @JsonProperty("role") GoalRole role,
        @JsonProperty("emailReminderRule") ReminderRule emailReminderRule,
        @JsonProperty("phoneReminderRule") ReminderRule phoneReminderRule
    ) {
        this.role = role;
        this.emailReminderRule = emailReminderRule;
        this.phoneReminderRule = phoneReminderRule;
    }

    public GoalRole getRole() {
        return role;
    }

    public ReminderRule getEmailReminderRule() {
        return emailReminderRule;
    }

    public ReminderRule getPhoneReminderRule() {
        return phoneReminderRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalRoleConfiguration that = (GoalRoleConfiguration) o;

        if (!emailReminderRule.equals(that.emailReminderRule)) return false;
        if (!phoneReminderRule.equals(that.phoneReminderRule)) return false;
        if (role != that.role) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = role.hashCode();
        result = 31 * result + emailReminderRule.hashCode();
        result = 31 * result + phoneReminderRule.hashCode();
        return result;
    }

}
