package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.configuration.BetConfiguration;
import com.clemble.casino.bet.configuration.BetConfigurationConvertible;
import com.clemble.casino.goal.lifecycle.configuration.rule.start.GoalStartRule;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.goal.lifecycle.configuration.rule.judge.JudgeRule;
import com.clemble.casino.lifecycle.configuration.rule.bet.BetRule;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.lifecycle.configuration.rule.time.MoveTimeRule;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalConfiguration implements Configuration, GoalConfigurationKeyAware, BetConfigurationConvertible {

    final private String configurationKey;
    final private Bid bid;
    final private BetRule betRule;
    final private JudgeRule judgeRule;
    final private GoalStartRule startRule;
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
        @JsonProperty("privacyRule") PrivacyRule privacyRule,
        @JsonProperty("startRule") GoalStartRule startRule) {
        this.configurationKey = configurationKey;
        this.bid = bid;
        this.betRule = betRule;
        this.startRule = startRule;
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

    public GoalStartRule getStartRule() {
        return startRule;
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
        if (!startRule.equals(that.startRule)) return false;

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
        result = 31 * result + startRule.hashCode();
        return result;
    }
}
