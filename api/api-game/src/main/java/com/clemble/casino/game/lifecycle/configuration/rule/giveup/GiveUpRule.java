package com.clemble.casino.game.lifecycle.configuration.rule.giveup;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRuleOptions;
import com.clemble.casino.game.lifecycle.configuration.rule.GameRule;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

public enum GiveUpRule implements GameRule {

    lost(0),
    all(100),
    half(50),
    third(33),
    quarter(25),
    tenth(10);

    final public static GiveUpRule DEFAULT = GiveUpRule.all;
    final public static ConfigurationRuleOptions<GiveUpRule> OPTIONS = new ConfigurationRuleOptions<GiveUpRule>(tenth, all, half, third, quarter, tenth);

    final public int percent;

    private GiveUpRule(int percent) {
        this.percent = percent;
    }

    public ConfigurationRuleOptions<GiveUpRule> getOptions() {
        return OPTIONS;
    }

}
