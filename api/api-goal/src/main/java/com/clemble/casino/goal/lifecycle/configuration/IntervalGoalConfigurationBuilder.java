package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.ConfigurationBuilder;
import com.clemble.casino.goal.lifecycle.configuration.rule.IntervalGoalRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by mavarazy on 2/6/15.
 */
public class IntervalGoalConfigurationBuilder implements ConfigurationBuilder {

    final private int basePrice;
    final private int basePercentage;
    final private int baseInterval;
    final private GoalConfiguration base;
    final private List<IntervalGoalRule> intervalRules;

    @JsonCreator
    public IntervalGoalConfigurationBuilder(
        @JsonProperty("base") GoalConfiguration base,
        @JsonProperty("basePrice") int basePrice,
        @JsonProperty("baseInterval") int baseInterval,
        @JsonProperty("basePercentage") int basePercentage,
        @JsonProperty("intervalRules") List<IntervalGoalRule> intervalRules) {
        this.base = base;
        this.basePrice = basePrice;
        this.baseInterval = baseInterval;
        this.basePercentage = basePercentage;
        this.intervalRules = intervalRules;
    }

    public Configuration getBase() {
        return base;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getBaseInterval() {
        return baseInterval;
    }

    public int getBasePercentage() {
        return basePercentage;
    }

    public List<IntervalGoalRule> getIntervalRules() {
        return intervalRules;
    }

    public GoalConfiguration toConfiguration(final int bet) {
        if (bet < basePrice)
            throw new IllegalArgumentException();
        // Step 1. Fetching appropriate bet
        GoalConfiguration configuration = base;
        int totalPercentage = basePercentage;
        // Step 1.1. Reducing until proper configuration found
        int betInterval = bet - basePrice;
        int currentInterval = baseInterval;
        for(int i = 0; betInterval > currentInterval; i++) {
            betInterval -= currentInterval;

            IntervalGoalRule intervalRule = intervalRules.get(i);
            totalPercentage += intervalRule.getPercentage();
            currentInterval = intervalRule.getInterval();
            configuration = configuration.appendRule(intervalRule.getRule());
        }
        // Step 2. Checking configuration
        return configuration.setBet(bet, totalPercentage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntervalGoalConfigurationBuilder)) return false;

        IntervalGoalConfigurationBuilder that = (IntervalGoalConfigurationBuilder) o;

        if (!base.equals(that.base)) return false;
        if (!intervalRules.equals(that.intervalRules)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = base.hashCode();
        result = 31 * result + intervalRules.hashCode();
        return result;
    }

}
