package com.clemble.casino.game;

import com.clemble.casino.game.unit.GameUnit;
import com.clemble.casino.player.PlayerAware;

public interface GamePlayerContext extends PlayerAware {

    public GamePlayerUnit getUnits();

    public GamePlayerClock getClock();

    public GamePlayerAccount getAccount();

}
