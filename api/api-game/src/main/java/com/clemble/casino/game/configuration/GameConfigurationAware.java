package com.clemble.casino.game.configuration;

import com.clemble.casino.configuration.ConfigurationAware;

public interface GameConfigurationAware extends ConfigurationAware<GameConfiguration> {

    GameConfiguration getConfiguration();

}
