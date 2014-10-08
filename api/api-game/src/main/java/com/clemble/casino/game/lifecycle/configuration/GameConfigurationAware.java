package com.clemble.casino.game.lifecycle.configuration;

import com.clemble.casino.lifecycle.configuration.ConfigurationAware;

public interface GameConfigurationAware extends ConfigurationAware<GameConfiguration> {

    GameConfiguration getConfiguration();

}
