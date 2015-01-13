package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.ReminderRule;
import com.clemble.casino.goal.lifecycle.management.GoalRole;
import com.clemble.casino.lifecycle.configuration.rule.bet.BetRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/16/14.
 */
@JsonTypeName("rule:role:configuration")
public class GoalRoleConfiguration implements GoalRule {

    final private Bid bid;
    final private int betDays;
    final private ReminderRule emailReminderRule;
    final private ReminderRule phoneReminderRule;

    @JsonCreator
    public GoalRoleConfiguration(
        @JsonProperty("bid") Bid bid,
        @JsonProperty("betDays") int betDays,
        @JsonProperty("emailReminderRule") ReminderRule emailReminderRule,
        @JsonProperty("phoneReminderRule") ReminderRule phoneReminderRule
    ) {
        this.bid = bid;
        this.betDays = betDays;
        this.emailReminderRule = emailReminderRule;
        this.phoneReminderRule = phoneReminderRule;
    }

    public Bid getBid() {
        return bid;
    }

    public int getBetDays() {
        return betDays;
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
        if (!bid.equals(that.bid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = emailReminderRule.hashCode();
        result = 31 * result + phoneReminderRule.hashCode();
        result = 31 * result + bid.hashCode();
        return result;
    }

}
