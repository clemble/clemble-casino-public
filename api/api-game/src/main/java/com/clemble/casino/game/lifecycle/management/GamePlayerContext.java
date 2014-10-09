package com.clemble.casino.game.lifecycle.management;

import com.clemble.casino.lifecycle.management.PlayerContext;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.lifecycle.configuration.rule.time.PlayerClock;

public interface GamePlayerContext extends PlayerContext {

    public GamePlayerUnit getUnits();

    public PlayerClock getClock();

    public GamePlayerAccount getAccount();

}
