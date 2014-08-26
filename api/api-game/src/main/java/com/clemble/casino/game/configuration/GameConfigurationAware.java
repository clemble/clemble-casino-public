package com.clemble.casino.game.configuration;

import com.clemble.casino.ConfigurationAware;

public interface GameConfigurationAware extends ConfigurationAware {

    GameConfiguration getConfiguration();

}
