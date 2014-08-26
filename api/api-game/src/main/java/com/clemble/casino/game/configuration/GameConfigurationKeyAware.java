package com.clemble.casino.game.configuration;

import java.io.Serializable;

public interface GameConfigurationKeyAware extends Serializable {

    public GameConfigurationKey getConfigurationKey();

}
