package com.clemble.casino.goal.lifecycle.configuration;

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
    final private List<GoalConfigurationValue<TimeoutRule>> totalTimeoutRule;
    final private List<GoalConfigurationValue<ReminderRule>> emailReminderRule;
    final private List<GoalConfigurationValue<ReminderRule>> phoneReminderRule;
    final private List<GoalConfigurationValue<PrivacyRule>> privacyRule;
    final private List<GoalConfigurationValue<TimeoutRule>> moveTimeoutRule;
    final private List<GoalConfigurationValue<GoalRoleConfiguration>> supporterConfiguration;
    final private List<GoalConfigurationValue<GoalRoleConfiguration>> observerConfiguration;
    final private List<GoalConfigurationValue<ShareRule>> shareRule;

    @JsonCreator
    public GoalConfigurationChoices(
        @JsonProperty("bid") List<Money> bid,
        @JsonProperty("totalTimeoutRule") List<GoalConfigurationValue<TimeoutRule>> totalTimeoutRule,
        @JsonProperty("moveTimeoutRule") List<GoalConfigurationValue<TimeoutRule>> moveTimeoutRule,
        @JsonProperty("emailReminderRule") List<GoalConfigurationValue<ReminderRule>> emailReminderRule,
        @JsonProperty("phoneReminderRule") List<GoalConfigurationValue<ReminderRule>> phoneReminderRule,
        @JsonProperty("privacyRule") List<GoalConfigurationValue<PrivacyRule>> privacyRule,
        @JsonProperty("supporterConfiguration") List<GoalConfigurationValue<GoalRoleConfiguration>> supporterConfiguration,
        @JsonProperty("observerConfiguration") List<GoalConfigurationValue<GoalRoleConfiguration>> observerConfiguration,
        @JsonProperty("shareRule") List<GoalConfigurationValue<ShareRule>> shareRule
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

    public List<GoalConfigurationValue<TimeoutRule>> getTotalTimeoutRule() {
        return totalTimeoutRule;
    }

    public List<GoalConfigurationValue<ReminderRule>> getEmailReminderRule() {
        return emailReminderRule;
    }

    public List<GoalConfigurationValue<ReminderRule>> getPhoneReminderRule() {
        return phoneReminderRule;
    }

    public List<GoalConfigurationValue<PrivacyRule>> getPrivacyRule() {
        return privacyRule;
    }

    public List<GoalConfigurationValue<TimeoutRule>> getMoveTimeoutRule() {
        return moveTimeoutRule;
    }

    public List<GoalConfigurationValue<GoalRoleConfiguration>> getSupporterConfiguration() {
        return supporterConfiguration;
    }

    public List<GoalConfigurationValue<GoalRoleConfiguration>> getObserverConfiguration() {
        return observerConfiguration;
    }

    public List<GoalConfigurationValue<ShareRule>> getShareRule() {
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
