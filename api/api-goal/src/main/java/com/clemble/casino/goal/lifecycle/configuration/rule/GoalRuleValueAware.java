package com.clemble.casino.goal.lifecycle.configuration.rule;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;

/**
 * Created by mavarazy on 2/6/15.
 */
public interface GoalRuleValueAware<T extends ConfigurationRule> {

    T getRule();

    int getPercentage();

}
