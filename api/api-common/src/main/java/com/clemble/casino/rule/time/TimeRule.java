package com.clemble.casino.rule.time;

import java.util.Date;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.rule.ConfigurationRule;
import com.clemble.casino.rule.breach.BreachPunishment;
import com.clemble.casino.rule.breach.BreachPunishmentAware;

public interface TimeRule extends ConfigurationRule, BreachPunishmentAware {

    public BreachPunishment getPunishment();

    public long getLimit();

    public long timeUntilBreach(long totalTimeSpent);

    public long timeUntilBreach(PlayerClock clock);

    public Date breachDate(PlayerClock clock);

    public PlayerAwareEvent toTimeBreachedEvent(String player);

}
