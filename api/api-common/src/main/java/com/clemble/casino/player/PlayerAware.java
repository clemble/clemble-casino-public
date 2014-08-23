package com.clemble.casino.player;

import com.clemble.casino.KeyAware;

import java.io.Serializable;

public interface PlayerAware extends KeyAware, Serializable {

    final public static String DEFAULT_PLAYER = "casino";
    final public static String PLAYER = "player";

    public String getPlayer();

}
