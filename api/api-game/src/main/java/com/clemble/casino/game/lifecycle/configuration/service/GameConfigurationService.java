package com.clemble.casino.game.lifecycle.configuration.service;

import com.clemble.casino.lifecycle.configuration.service.ConfigurationService;
import com.clemble.casino.game.lifecycle.configuration.GameConfiguration;

import java.util.List;

public interface GameConfigurationService extends ConfigurationService<GameConfiguration> {

    public List<GameConfiguration> getConfigurations();

}
