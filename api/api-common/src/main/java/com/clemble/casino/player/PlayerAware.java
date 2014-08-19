package com.clemble.casino.player;

import com.clemble.casino.KeyAware;

import java.io.Serializable;

public interface PlayerAware extends KeyAware, Serializable {

    final public String DEFAULT_PLAYER = "casino";
    final public String PLAYER = "player";

    public String getPlayer();

}
