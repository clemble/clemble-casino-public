package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.configuration.BetConfiguration;
import com.clemble.casino.bet.configuration.BetConfigurationConvertible;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.EmailReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.PhoneReminderRule;
import com.clemble.casino.goal.lifecycle.management.GoalRole;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.rule.bet.BetRule;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.lifecycle.configuration.rule.time.MoveTimeRule;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

/**
 * Created by mavarazy on 12/13/14.
 */
@JsonTypeName("short")
public class GoalConfiguration implements
    Configuration,
    GoalConfigurationKeyAware,
    BetConfigurationConvertible {

    final private String configurationKey;
    final private Bid bid;
    final private BetRule betRule;
    final private PrivacyRule privacyRule;
    final private MoveTimeRule moveTimeRule;
    final private TotalTimeRule totalTimeRule;

    final private List<GoalRoleConfiguration> roleConfigurations;

    final private List<ChallengeConfiguration> challenges;

    @JsonCreator
    public GoalConfiguration(
        @JsonProperty("configurationKey") String configurationKey,
        @JsonProperty("bid") Bid bid,
        @JsonProperty("betRule") BetRule betRule,
        @JsonProperty("moveTimeRule") MoveTimeRule moveTimeRule,
        @JsonProperty("totalTimeRule") TotalTimeRule totalTimeRule,
        @JsonProperty("privacyRule") PrivacyRule privacyRule,
        @JsonProperty("roleConfigurations") List<GoalRoleConfiguration> roleConfigurations,
        @JsonProperty("challenges") List<ChallengeConfiguration> challenges
    ) {
        this.configurationKey = configurationKey;
        this.bid = bid;
        this.betRule = betRule;
        this.moveTimeRule = moveTimeRule;
        this.totalTimeRule = totalTimeRule;
        this.privacyRule = privacyRule;
        this.roleConfigurations = roleConfigurations;
        this.challenges = challenges;
    }

    @Override
    public String getConfigurationKey() {
        return configurationKey;
    }

    public Bid getBid() {
        return bid;
    }

    public BetRule getBetRule() {
        return betRule;
    }

    public MoveTimeRule getMoveTimeRule() {
        return moveTimeRule;
    }

    public TotalTimeRule getTotalTimeRule() {
        return totalTimeRule;
    }

    public List<GoalRoleConfiguration> getRoleConfigurations() {
        return roleConfigurations;
    }

    @Override
    public PrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    public List<ChallengeConfiguration> getChallenges() {
        return challenges;
    }

    public BetConfiguration toBetConfiguration() {
        return new BetConfiguration(privacyRule, betRule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfiguration that = (GoalConfiguration) o;

        if (!betRule.equals(that.betRule)) return false;
        if (!bid.equals(that.bid)) return false;
        if (!moveTimeRule.equals(that.moveTimeRule)) return false;
        if (privacyRule != that.privacyRule) return false;
        if (!totalTimeRule.equals(that.totalTimeRule)) return false;
        if (!roleConfigurations.equals(that.roleConfigurations)) return false;
        if (!challenges.equals(that.challenges)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid.hashCode();
        result = 31 * result + betRule.hashCode();
        result = 31 * result + privacyRule.hashCode();
        result = 31 * result + moveTimeRule.hashCode();
        result = 31 * result + totalTimeRule.hashCode();
        result = 31 * result + roleConfigurations.hashCode();
        result = 31 * result + challenges.hashCode();
        return result;
    }

}
