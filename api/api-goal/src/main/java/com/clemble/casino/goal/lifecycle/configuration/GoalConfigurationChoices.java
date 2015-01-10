package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.ReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.share.ShareRule;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.lifecycle.configuration.rule.timeout.TimeoutRule;
import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by mavarazy on 1/10/15.
 */
public class GoalConfigurationChoices {

    final private List<Money> bid;
    final private GoalConfigurationOption<TimeoutRule> totalTimeoutRule;
    final private GoalConfigurationOption<ReminderRule> emailReminderRule;
    final private GoalConfigurationOption<ReminderRule> phoneReminderRule;
    final private GoalConfigurationOption<PrivacyRule> privacyRule;
    final private GoalConfigurationOption<TimeoutRule> moveTimeoutRule;
    final private GoalConfigurationOption<GoalRoleConfiguration> supporterConfiguration;
    final private GoalConfigurationOption<GoalRoleConfiguration> observerConfiguration;
    final private GoalConfigurationOption<ShareRule> shareRule;

    @JsonCreator
    public GoalConfigurationChoices(
        @JsonProperty("bid") List<Money> bid,
        @JsonProperty("totalTimeoutRule") GoalConfigurationOption<TimeoutRule> totalTimeoutRule,
        @JsonProperty("moveTimeoutRule") GoalConfigurationOption<TimeoutRule> moveTimeoutRule,
        @JsonProperty("emailReminderRule") GoalConfigurationOption<ReminderRule> emailReminderRule,
        @JsonProperty("phoneReminderRule") GoalConfigurationOption<ReminderRule> phoneReminderRule,
        @JsonProperty("privacyRule") GoalConfigurationOption<PrivacyRule> privacyRule,
        @JsonProperty("supporterConfiguration") GoalConfigurationOption<GoalRoleConfiguration> supporterConfiguration,
        @JsonProperty("observerConfiguration") GoalConfigurationOption<GoalRoleConfiguration> observerConfiguration,
        @JsonProperty("shareRule") GoalConfigurationOption<ShareRule> shareRule
    ) {
        this.bid = bid;
        this.totalTimeoutRule = totalTimeoutRule;
        this.emailReminderRule = emailReminderRule;
        this.phoneReminderRule = phoneReminderRule;
        this.privacyRule = privacyRule;
        this.moveTimeoutRule = moveTimeoutRule;
        this.supporterConfiguration = supporterConfiguration;
        this.observerConfiguration = observerConfiguration;
        this.shareRule = shareRule;
    }

    public List<Money> getBid() {
        return bid;
    }

    public GoalConfigurationOption<TimeoutRule> getTotalTimeoutRule() {
        return totalTimeoutRule;
    }

    public GoalConfigurationOption<ReminderRule> getEmailReminderRule() {
        return emailReminderRule;
    }

    public GoalConfigurationOption<ReminderRule> getPhoneReminderRule() {
        return phoneReminderRule;
    }

    public GoalConfigurationOption<PrivacyRule> getPrivacyRule() {
        return privacyRule;
    }

    public GoalConfigurationOption<TimeoutRule> getMoveTimeoutRule() {
        return moveTimeoutRule;
    }

    public GoalConfigurationOption<GoalRoleConfiguration> getSupporterConfiguration() {
        return supporterConfiguration;
    }

    public GoalConfigurationOption<GoalRoleConfiguration> getObserverConfiguration() {
        return observerConfiguration;
    }

    public GoalConfigurationOption<ShareRule> getShareRule() {
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
        if (!observerConfiguration.equals(that.observerConfiguration)) return false;
        if (!phoneReminderRule.equals(that.phoneReminderRule)) return false;
        if (!privacyRule.equals(that.privacyRule)) return false;
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
        result = 31 * result + privacyRule.hashCode();
        result = 31 * result + moveTimeoutRule.hashCode();
        result = 31 * result + supporterConfiguration.hashCode();
        result = 31 * result + observerConfiguration.hashCode();
        result = 31 * result + shareRule.hashCode();
        return result;
    }

}
