package com.clemble.casino.game.lifecycle.configuration.rule.outcome;

import com.clemble.casino.game.lifecycle.configuration.rule.GameRule;
import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRuleOptions;

/**
 * Created by mavarazy on 23/12/13.
 */
public enum DrawRule implements GameRule {

    owned,
    spent;

    final static private ConfigurationRuleOptions<DrawRule> OPTIONS = new ConfigurationRuleOptions<DrawRule>(DrawRule.owned, DrawRule.owned, DrawRule.spent);

    public ConfigurationRuleOptions getOptions() {
        return OPTIONS;
    }

}
