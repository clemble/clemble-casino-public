package com.clemble.casino.game.rule.time;

import com.clemble.casino.game.event.client.GameAction;
import com.clemble.casino.game.rule.GameRule;

public interface TimeRule extends GameRule {

    public TimeBreachPunishment getPunishment();

    public long getLimit();

    public long getBreachTime(long totalTimeSpent);

    public GameAction toTimeBreachedEvent(String player);

}