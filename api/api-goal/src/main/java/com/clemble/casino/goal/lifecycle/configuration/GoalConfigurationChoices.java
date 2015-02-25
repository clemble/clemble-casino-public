package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRuleValue;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.ReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.share.ShareRule;
import com.clemble.casino.lifecycle.configuration.ConfigurationBuilder;
import com.clemble.casino.lifecycle.configuration.rule.timeout.TimeoutRule;
import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by mavarazy on 1/10/15.
 */
public class GoalConfigurationChoices implements ConfigurationBuilder {

    final private List<Money> bid;
    final private List<GoalRuleValue<TimeoutRule>> totalTimeoutRule;
    final private List<GoalRuleValue<ReminderRule>> emailReminderRule;
    final private List<GoalRuleValue<ReminderRule>> phoneReminderRule;
    final private List<GoalRuleValue<TimeoutRule>> moveTimeoutRule;
    final private List<GoalRuleValue<GoalRoleConfiguration>> supporterConfiguration;
    final private List<GoalRuleValue<ShareRule>> shareRule;

    @JsonCreator
    public GoalConfigurationChoices(
        @JsonProperty("bid") List<Money> bid,
        @JsonProperty("totalTimeoutRule") List<GoalRuleValue<TimeoutRule>> totalTimeoutRule,
        @JsonProperty("moveTimeoutRule") List<GoalRuleValue<TimeoutRule>> moveTimeoutRule,
        @JsonProperty("emailReminderRule") List<GoalRuleValue<ReminderRule>> emailReminderRule,
        @JsonProperty("phoneReminderRule") List<GoalRuleValue<ReminderRule>> phoneReminderRule,
        @JsonProperty("supporterConfiguration") List<GoalRuleValue<GoalRoleConfiguration>> supporterConfiguration,
        @JsonProperty("shareRule") List<GoalRuleValue<ShareRule>> shareRule
    ) {
        this.bid = bid;
        this.totalTimeoutRule = totalTimeoutRule;
        this.emailReminderRule = emailReminderRule;
        this.phoneReminderRule = phoneReminderRule;
        this.moveTimeoutRule = moveTimeoutRule;
        this.supporterConfiguration = supporterConfiguration;
        this.shareRule = shareRule;
    }

    public List<Money> getBid() {
        return bid;
    }

    public List<GoalRuleValue<TimeoutRule>> getTotalTimeoutRule() {
        return totalTimeoutRule;
    }

    public List<GoalRuleValue<ReminderRule>> getEmailReminderRule() {
        return emailReminderRule;
    }

    public List<GoalRuleValue<ReminderRule>> getPhoneReminderRule() {
        return phoneReminderRule;
    }

    public List<GoalRuleValue<TimeoutRule>> getMoveTimeoutRule() {
        return moveTimeoutRule;
    }

    public List<GoalRuleValue<GoalRoleConfiguration>> getSupporterConfiguration() {
        return supporterConfiguration;
    }

    public List<GoalRuleValue<ShareRule>> getShareRule() {
        return shareRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfigurationChoices that = (GoalConfigurationChoices) o;

        if (!bid.equals(that.bid)) return false;
        if (!emailReminderRule.equals(that.emailReminderRule)) return false;
        if (!moveTimeoutRule.equals(that.moveTimeoutRule)) return false;
        if (!phoneReminderRule.equals(that.phoneReminderRule)) return false;
        if (!shareRule.equals(that.shareRule)) return false;
        if (!supporterConfiguration.equals(that.supporterConfiguration)) return false;
        if (!totalTimeoutRule.equals(that.totalTimeoutRule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid.hashCode();
        result = 31 * result + totalTimeoutRule.hashCode();
        result = 31 * result + emailReminderRule.hashCode();
        result = 31 * result + phoneReminderRule.hashCode();
        result = 31 * result + moveTimeoutRule.hashCode();
        result = 31 * result + supporterConfiguration.hashCode();
        result = 31 * result + shareRule.hashCode();
        return result;
    }

}
