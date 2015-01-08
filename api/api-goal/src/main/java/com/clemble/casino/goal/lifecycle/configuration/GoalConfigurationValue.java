package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.money.Money;
import com.clemble.casino.payment.AmountAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 1/6/15.
 */
public class GoalConfigurationValue<T extends ConfigurationRule> implements AmountAware {

    final private T rule;
    final private Money amount;

    @JsonCreator
    public GoalConfigurationValue(@JsonProperty("rule") T rule, @JsonProperty("amount") Money amount) {
        this.rule = rule;
        this.amount = amount;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    public T getRule() {
        return rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfigurationValue that = (GoalConfigurationValue) o;

        if (!amount.equals(that.amount)) return false;
        if (!rule.equals(that.rule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rule.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

}
