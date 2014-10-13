package com.clemble.casino.lifecycle.management;

import com.clemble.casino.lifecycle.configuration.rule.time.MovePlayerClock;
import com.clemble.casino.lifecycle.configuration.rule.time.PlayerClock;
import com.clemble.casino.player.PlayerAware;

/**
 * Created by mavarazy on 10/9/14.
 */
public interface PlayerContext extends PlayerAware {

    public PlayerClock getClock();

    public MovePlayerClock getMoveClock();

}
