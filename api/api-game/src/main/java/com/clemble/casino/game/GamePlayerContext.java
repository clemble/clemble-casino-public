package com.clemble.casino.game;

import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.rule.time.PlayerClock;

public interface GamePlayerContext extends PlayerAware {

    public GamePlayerUnit getUnits();

    public PlayerClock getClock();

    public GamePlayerAccount getAccount();

}
