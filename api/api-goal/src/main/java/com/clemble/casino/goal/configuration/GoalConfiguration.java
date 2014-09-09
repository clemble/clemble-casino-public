package com.clemble.casino.goal.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.configuration.BetConfiguration;
import com.clemble.casino.bet.configuration.BetConfigurationConvertible;
import com.clemble.casino.configuration.Configuration;
import com.clemble.casino.money.Money;
import com.clemble.casino.rule.bet.BetRule;
import com.clemble.casino.rule.privacy.PrivacyRule;
import com.clemble.casino.rule.time.MoveTimeRule;
import com.clemble.casino.rule.time.TotalTimeRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 8/26/14.
 */
@JsonTypeName("goal")
public class GoalConfiguration implements Configuration, BetConfigurationConvertible {

    final private Bid bid;
    final private BetRule betRule;
    final private PrivacyRule privacyRule;
    final private MoveTimeRule moveTimeRule;
    final private TotalTimeRule totalTimeRule;

    @JsonCreator
    public GoalConfiguration(
        @JsonProperty("price") Bid bid,
        @JsonProperty("betRule") BetRule betRule,
        @JsonProperty("moveTimeRule") MoveTimeRule moveTimeRule,
        @JsonProperty("totalTimeRule") TotalTimeRule totalTimeRule,
        @JsonProperty("privacyRule") PrivacyRule privacyRule) {
        this.bid = bid;
        this.betRule = betRule;
        this.moveTimeRule = moveTimeRule;
        this.totalTimeRule = totalTimeRule;
        this.privacyRule = privacyRule;
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

    @Override
    public PrivacyRule getPrivacyRule() {
        return privacyRule;
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
        if (privacyRule != that.privacyRule) return false;
        if (!moveTimeRule.equals(that.moveTimeRule)) return false;
        if (!totalTimeRule.equals(that.totalTimeRule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = betRule.hashCode();
        result = 31 * result + moveTimeRule.hashCode();
        result = 31 * result + totalTimeRule.hashCode();
        result = 31 * result + privacyRule.hashCode();
        return result;
    }

}
