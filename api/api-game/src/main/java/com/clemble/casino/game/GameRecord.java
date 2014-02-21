package com.clemble.casino.game;

import java.io.Serializable;

import com.clemble.casino.game.specification.GameConfigurationKeyAware;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
public interface GameRecord extends GameConfigurationKeyAware, GameSessionAware, Serializable {

    public GameSessionState getSessionState();

}
