package com.clemble.casino.game.lifecycle.configuration.rule.outcome;

import com.clemble.casino.game.lifecycle.configuration.rule.GameRule;
import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRuleOptions;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 23/12/13.
 */
@JsonTypeName("rule:game:draw")
public enum DrawRule implements GameRule {

    owned,
    spent;

    final static private ConfigurationRuleOptions<DrawRule> OPTIONS = new ConfigurationRuleOptions<DrawRule>(DrawRule.owned, DrawRule.owned, DrawRule.spent);

    public ConfigurationRuleOptions getOptions() {
        return OPTIONS;
    }

}
