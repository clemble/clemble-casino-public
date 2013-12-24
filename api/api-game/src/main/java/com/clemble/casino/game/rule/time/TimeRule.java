package com.clemble.casino.game.rule.time;

import com.clemble.casino.game.GamePlayerClock;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.rule.GameRule;

public interface TimeRule extends GameRule {

    public TimeBreachPunishment getPunishment();

    public long getLimit();

    public long timeUntilBreach(long totalTimeSpent);

    public long timeUntilBreach(GamePlayerClock clock);

    public GameAction toTimeBreachedEvent(String player);

}