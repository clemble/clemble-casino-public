package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.configuration.BetConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.EmailReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.PhoneReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.start.GoalStartRule;
import com.clemble.casino.lifecycle.configuration.rule.bet.BetRule;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.lifecycle.configuration.rule.time.MoveTimeRule;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/13/14.
 */
@JsonTypeName("long")
public class LongGoalConfiguration implements GoalConfiguration {

    final private String configurationKey;
    final private Bid bid;
    final private BetRule betRule;

    final private MoveTimeRule moveTimeRule;
    final private TotalTimeRule totalTimeRule;

    final private PrivacyRule privacyRule;

    final private EmailReminderRule emailReminderRule;
    final private PhoneReminderRule phoneReminderRule;

    final private GoalStartRule startRule;

    @JsonCreator
    public LongGoalConfiguration(
        @JsonProperty("configurationKey") String configurationKey,
        @JsonProperty("bid") Bid bid,
        @JsonProperty("betRule") BetRule betRule,
        @JsonProperty("moveTimeRule") MoveTimeRule moveTimeRule,
        @JsonProperty("totalTimeRule") TotalTimeRule totalTimeRule,
        @JsonProperty("privacyRule") PrivacyRule privacyRule,
        @JsonProperty("startRule") GoalStartRule startRule,
        @JsonProperty("emailReminderRule") EmailReminderRule emailReminderRule,
        @JsonProperty("phoneReminderRule") PhoneReminderRule phoneReminderRule
    ) {
        this.bid  = bid;
        this.betRule = betRule;
        this.privacyRule = privacyRule;
        this.moveTimeRule = moveTimeRule;
        this.totalTimeRule = totalTimeRule;
        this.configurationKey = configurationKey;
        this.startRule = startRule;
        this.emailReminderRule = emailReminderRule;
        this.phoneReminderRule = phoneReminderRule;
    }

    @Override
    public String getConfigurationKey() {
        return configurationKey;
    }

    @Override
    public Bid getBid() {
        return bid;
    }

    @Override
    public BetRule getBetRule() {
        return betRule;
    }

    @Override
    public GoalStartRule getStartRule() {
        return startRule;
    }

    @Override
    public MoveTimeRule getMoveTimeRule() {
        return moveTimeRule;
    }

    @Override
    public TotalTimeRule getTotalTimeRule() {
        return totalTimeRule;
    }

    @Override
    public EmailReminderRule getEmailReminderRule() {
        return emailReminderRule;
    }

    @Override
    public PhoneReminderRule getPhoneReminderRule() {
        return phoneReminderRule;
    }

    @Override
    public PrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    @Override
    public BetConfiguration toBetConfiguration() {
        return new BetConfiguration(privacyRule, betRule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LongGoalConfiguration that = (LongGoalConfiguration) o;

        if (!bid.equals(that.bid)) return false;
        if (!privacyRule.equals(that.privacyRule)) return false;
        if (!phoneReminderRule.equals(that.phoneReminderRule)) return false;
        if (!emailReminderRule.equals(that.emailReminderRule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid.hashCode();
        result = 31 * result + configurationKey.hashCode();
        return result;
    }
}
