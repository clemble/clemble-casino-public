package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.bet.Bet;
import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.ReminderRule;
import com.clemble.casino.lifecycle.configuration.rule.bet.BetRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/16/14.
 */
@JsonTypeName("rule:role:configuration")
public class GoalRoleConfiguration implements GoalRule {

    final private int betDays;
    final private BetRule betRule;
    final private int percentage;
    final private ReminderRule emailReminderRule;
    final private ReminderRule phoneReminderRule;

    @JsonCreator
    public GoalRoleConfiguration(
        @JsonProperty("betDays") int betDays,
        @JsonProperty("betRule") BetRule betRule,
        @JsonProperty("percentage") int percentage,
        @JsonProperty("emailReminderRule") ReminderRule emailReminderRule,
        @JsonProperty("phoneReminderRule") ReminderRule phoneReminderRule
    ) {
        this.betRule = betRule;
        this.betDays = betDays;
        this.percentage = percentage;
        this.emailReminderRule = emailReminderRule;
        this.phoneReminderRule = phoneReminderRule;
    }

    public BetRule getBetRule() {
        return betRule;
    }

    public int getBetDays() {
        return betDays;
    }

    public int getPercentage() {
        return percentage;
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
        if (!betRule.equals(that.betRule)) return false;
        return percentage == percentage;

    }

    @Override
    public int hashCode() {
        int result = emailReminderRule.hashCode();
        result = 31 * result + phoneReminderRule.hashCode();
        result = 31 * result + betRule.hashCode();
        result = 31 * result + percentage;
        return result;
    }

}
