package com.clemble.casino.game;

import java.io.Serializable;

import com.clemble.casino.game.specification.GameConfigurationKeyAware;

public interface GameRecord extends GameConfigurationKeyAware, GameSessionAware, Serializable {

    public GameSessionState getSessionState();

}
