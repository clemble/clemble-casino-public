package com.clemble.casino.game.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.configuration.ConfigurationService;
import com.clemble.casino.game.configuration.GameConfiguration;

import java.util.List;

public interface GameConfigurationService extends ConfigurationService<GameConfiguration>, ClembleService {

    public List<GameConfiguration> getConfigurations();

}
