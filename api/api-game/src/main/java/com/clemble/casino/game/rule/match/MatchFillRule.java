package com.clemble.casino.game.rule.match;

import com.clemble.casino.game.rule.MatchRule;
import com.clemble.casino.rule.ConfigurationRuleOptions;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("matchFillRule")
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
