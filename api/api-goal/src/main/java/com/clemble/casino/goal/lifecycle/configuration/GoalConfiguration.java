package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.ReminderRule;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.lifecycle.configuration.rule.time.MoveTimeRule;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
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
    final private Bid bid;
    final private ReminderRule emailReminderRule;
    final private ReminderRule phoneReminderRule;
    final private PrivacyRule privacyRule;
    final private MoveTimeRule moveTimeRule;
    final private TotalTimeRule totalTimeRule;
    final private GoalRoleConfiguration supporterConfiguration;
    final private GoalRoleConfiguration observerConfiguration;

    @JsonCreator
    public GoalConfiguration(
        @JsonProperty("configurationKey") String configurationKey,
        @JsonProperty("bid") Bid bid,
        @JsonProperty("emailReminderRule") ReminderRule emailReminderRule,
        @JsonProperty("phoneReminderRule") ReminderRule phoneReminderRule,
        @JsonProperty("moveTimeRule") MoveTimeRule moveTimeRule,
        @JsonProperty("totalTimeRule") TotalTimeRule totalTimeRule,
        @JsonProperty("privacyRule") PrivacyRule privacyRule,
        @JsonProperty("supporterConfiguration") GoalRoleConfiguration supporterConfiguration,
        @JsonProperty("observerConfiguration") GoalRoleConfiguration observerConfiguration
    ) {
        this.configurationKey = configurationKey;
        this.bid = bid;
        this.emailReminderRule = emailReminderRule;
        this.phoneReminderRule = phoneReminderRule;
        this.moveTimeRule = moveTimeRule;
        this.totalTimeRule = totalTimeRule;
        this.privacyRule = privacyRule;
        this.supporterConfiguration = supporterConfiguration;
        this.observerConfiguration = observerConfiguration;
    }

    @Override
    public String getConfigurationKey() {
        return configurationKey;
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

    public MoveTimeRule getMoveTimeRule() {
        return moveTimeRule;
    }

    public TotalTimeRule getTotalTimeRule() {
        return totalTimeRule;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfiguration that = (GoalConfiguration) o;

        if (!moveTimeRule.equals(that.moveTimeRule)) return false;
        if (privacyRule != that.privacyRule) return false;
        if (!totalTimeRule.equals(that.totalTimeRule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = privacyRule.hashCode();
        result = 31 * result + moveTimeRule.hashCode();
        result = 31 * result + totalTimeRule.hashCode();
        return result;
    }

}
