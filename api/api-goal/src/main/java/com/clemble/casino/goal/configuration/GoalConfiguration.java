package com.clemble.casino.goal.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.configuration.BetConfiguration;
import com.clemble.casino.bet.configuration.BetConfigurationConvertible;
import com.clemble.casino.configuration.Configuration;
import com.clemble.casino.goal.rule.judge.JudgeRule;
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
public class GoalConfiguration implements Configuration, GoalConfigurationKeyAware, BetConfigurationConvertible {

    final private String configurationKey;
    final private Bid bid;
    final private BetRule betRule;
    final private JudgeRule judgeRule;
    final private PrivacyRule privacyRule;
    final private MoveTimeRule moveTimeRule;
    final private TotalTimeRule totalTimeRule;

    @JsonCreator
    public GoalConfiguration(
        @JsonProperty("configurationKey") String configurationKey,
        @JsonProperty("bid") Bid bid,
        @JsonProperty("betRule") BetRule betRule,
        @JsonProperty("judgeRule") JudgeRule judgeRule,
        @JsonProperty("moveTimeRule") MoveTimeRule moveTimeRule,
        @JsonProperty("totalTimeRule") TotalTimeRule totalTimeRule,
        @JsonProperty("privacyRule") PrivacyRule privacyRule) {
        this.configurationKey = configurationKey;
        this.bid = bid;
        this.betRule = betRule;
        this.judgeRule = judgeRule;
        this.moveTimeRule = moveTimeRule;
        this.totalTimeRule = totalTimeRule;
        this.privacyRule = privacyRule;
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

    public JudgeRule getJudgeRule() {
        return judgeRule;
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
        if (!bid.equals(that.bid)) return false;
        if (!judgeRule.equals(that.judgeRule)) return false;
        if (!moveTimeRule.equals(that.moveTimeRule)) return false;
        if (privacyRule != that.privacyRule) return false;
        if (!totalTimeRule.equals(that.totalTimeRule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid.hashCode();
        result = 31 * result + betRule.hashCode();
        result = 31 * result + judgeRule.hashCode();
        result = 31 * result + privacyRule.hashCode();
        result = 31 * result + moveTimeRule.hashCode();
        result = 31 * result + totalTimeRule.hashCode();
        return result;
    }
}
