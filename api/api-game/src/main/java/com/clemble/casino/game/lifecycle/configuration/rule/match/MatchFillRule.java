package com.clemble.casino.game.lifecycle.configuration.rule.match;

import com.clemble.casino.game.lifecycle.configuration.rule.MatchRule;
import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRuleOptions;
import com.fasterxml.jackson.annotation.JsonTypeName;

public enum MatchFillRule implements MatchRule {

    reminder,
    maxcommon,
    none;

    final private static ConfigurationRuleOptions<MatchFillRule> OPTIONS =
        new ConfigurationRuleOptions<MatchFillRule>(maxcommon, reminder, maxcommon, none);

    public ConfigurationRuleOptions<MatchFillRule> getOptions() {
        return OPTIONS;
    }

}
