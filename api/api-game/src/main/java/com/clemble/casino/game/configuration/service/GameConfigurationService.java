package com.clemble.casino.game.configuration.service;

import com.clemble.casino.configuration.service.ConfigurationService;
import com.clemble.casino.game.configuration.GameConfiguration;

import java.util.List;

public interface GameConfigurationService extends ConfigurationService<GameConfiguration> {

    public List<GameConfiguration> getConfigurations();

}
