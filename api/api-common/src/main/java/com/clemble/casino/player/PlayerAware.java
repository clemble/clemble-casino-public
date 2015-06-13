package com.clemble.casino.player;

import com.clemble.casino.KeyAware;

import java.io.Serializable;

public interface PlayerAware extends KeyAware, Serializable {

    String DEFAULT_PLAYER = "casino";
    String PLAYER = "player";

    String getPlayer();

}
