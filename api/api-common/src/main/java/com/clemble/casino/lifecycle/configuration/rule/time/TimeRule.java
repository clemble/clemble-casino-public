package com.clemble.casino.lifecycle.configuration.rule.time;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.clemble.casino.lifecycle.configuration.rule.breach.BreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.BreachPunishmentAware;

public interface TimeRule extends ConfigurationRule, BreachPunishmentAware {

    BreachPunishment getPunishment();

    long getLimit();

    long timeUntilBreach(PlayerClock clock);

}
