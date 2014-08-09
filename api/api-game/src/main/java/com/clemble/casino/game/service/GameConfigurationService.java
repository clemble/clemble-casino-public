package com.clemble.casino.game.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.specification.GameConfigurations;

public interface GameConfigurationService extends ClembleService {

    public GameConfigurations getConfigurations();

}
