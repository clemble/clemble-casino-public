package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.ReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.share.ShareRule;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.lifecycle.configuration.rule.timeout.TimeoutRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/13/14.
 */
@JsonTypeName("short")
public class GoalConfiguration implements
    Configuration,
    GoalConfigurationKeyAware {

    final private String configurationKey;
    final private String name;
    final private Bid bid;
    final private ReminderRule emailReminderRule;
    final private ReminderRule phoneReminderRule;
    final private PrivacyRule privacyRule;
    final private TimeoutRule moveTimeoutRule;
    final private TimeoutRule totalTimeoutRule;
    final private GoalRoleConfiguration supporterConfiguration;
    final private GoalRoleConfiguration observerConfiguration;
    final private ShareRule shareRule;

    @JsonCreator
    public GoalConfiguration(
        @JsonProperty("configurationKey") String configurationKey,
        @JsonProperty("name") String name,
        @JsonProperty("bid") Bid bid,
        @JsonProperty("emailReminderRule") ReminderRule emailReminderRule,
        @JsonProperty("phoneReminderRule") ReminderRule phoneReminderRule,
        @JsonProperty("moveTimeRule") TimeoutRule moveTimeoutRule,
        @JsonProperty("totalTimeRule") TimeoutRule totalTimeoutRule,
        @JsonProperty("privacyRule") PrivacyRule privacyRule,
        @JsonProperty("supporterConfiguration") GoalRoleConfiguration supporterConfiguration,
        @JsonProperty("observerConfiguration") GoalRoleConfiguration observerConfiguration,
        @JsonProperty("shareRule") ShareRule shareRule
    ) {
        this.configurationKey = configurationKey;
        this.name = name;
        this.bid = bid;
        this.emailReminderRule = emailReminderRule;
        this.phoneReminderRule = phoneReminderRule;
        this.moveTimeoutRule = moveTimeoutRule;
        this.totalTimeoutRule = totalTimeoutRule;
        this.privacyRule = privacyRule;
        this.supporterConfiguration = supporterConfiguration;
        this.observerConfiguration = observerConfiguration;
        this.shareRule = shareRule;
    }

    @Override
    public String getConfigurationKey() {
        return configurationKey;
    }

    public String getName() {
        return name;
    }

    public Bid getBid() {
        return bid;
    }

    public ReminderRule getEmailReminderRule() {
        return emailReminderRule;
    }

    public ReminderRule getPhoneReminderRule() {
        return phoneReminderRule;
    }

    public TimeoutRule getMoveTimeoutRule() {
        return moveTimeoutRule;
    }

    public TimeoutRule getTotalTimeoutRule() {
        return totalTimeoutRule;
    }

    public GoalRoleConfiguration getSupporterConfiguration() {
        return supporterConfiguration;
    }

    public GoalRoleConfiguration getObserverConfiguration() {
        return observerConfiguration;
    }

    @Override
    public PrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    public ShareRule getShareRule() {
        return shareRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfiguration that = (GoalConfiguration) o;

        if (!moveTimeoutRule.equals(that.moveTimeoutRule)) return false;
        if (privacyRule != that.privacyRule) return false;
        if (!totalTimeoutRule.equals(that.totalTimeoutRule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = privacyRule.hashCode();
        result = 31 * result + moveTimeoutRule.hashCode();
        result = 31 * result + totalTimeoutRule.hashCode();
        return result;
    }

}
