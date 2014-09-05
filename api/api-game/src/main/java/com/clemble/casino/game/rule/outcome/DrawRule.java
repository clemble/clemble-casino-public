package com.clemble.casino.game.rule.outcome;

import com.clemble.casino.game.rule.GameRule;
import com.clemble.casino.game.rule.RoundRule;
import com.clemble.casino.rule.ConfigurationRule;
import com.clemble.casino.rule.ConfigurationRuleOptions;
import com.clemble.casino.utils.CollectionUtils;

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
