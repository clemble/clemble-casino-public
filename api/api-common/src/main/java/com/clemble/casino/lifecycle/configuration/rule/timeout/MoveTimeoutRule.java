package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.clemble.casino.lifecycle.configuration.rule.breach.BreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.BreachPunishmentAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 5/11/15.
 */
@JsonTypeName("rule:timeout:move")
public class MoveTimeoutRule implements ConfigurationRule, BreachPunishmentAware {

    final private BreachPunishment punishment;
    final private MoveTimeoutCalculator timeoutCalculator;

    @JsonCreator
    public MoveTimeoutRule(
        @JsonProperty("punishment") BreachPunishment punishment,
        @JsonProperty("timeoutCalculator") MoveTimeoutCalculator timeoutCalculator) {
        this.punishment = punishment;
        this.timeoutCalculator = timeoutCalculator;
    }

    @Override
    public BreachPunishment getPunishment() {
        return punishment;
    }

    public TimeoutCalculator getTimeoutCalculator() {
        return timeoutCalculator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoveTimeoutRule that = (MoveTimeoutRule) o;

        if (!punishment.equals(that.punishment)) return false;
        if (!timeoutCalculator.equals(that.timeoutCalculator)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = punishment.hashCode();
        result = 31 * result + timeoutCalculator.hashCode();
        return result;
    }

}
