package com.clemble.casino.game.lifecycle.management;

import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.lifecycle.configuration.rule.time.PlayerClock;

public interface GamePlayerContext extends PlayerAware {

    public GamePlayerUnit getUnits();

    public PlayerClock getClock();

    public GamePlayerAccount getAccount();

}
