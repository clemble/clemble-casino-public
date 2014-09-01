package com.clemble.casino.game.configuration;

import com.clemble.casino.KeyAware;

import java.io.Serializable;

public interface GameConfigurationKeyAware extends KeyAware {

    public String getConfigurationKey();

}
