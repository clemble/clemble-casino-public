package com.clemble.casino.game;

import com.clemble.casino.player.PlayerAware;

public interface GamePlayerContext extends PlayerAware {

    public GamePlayerClock getClock();

    public GamePlayerAccount getAccount();

}
