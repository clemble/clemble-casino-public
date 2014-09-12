package com.clemble.casino.game.construction.service;

import com.clemble.casino.construction.service.ConstructionService;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.game.construction.GameConstruction;
import com.clemble.casino.game.construction.GameConstructionRequest;

import java.util.Collection;

public interface GameConstructionService<T extends GameConstructionRequest> extends ConstructionService<GameConfiguration, T> {

    GameConstruction construct(T request);

    Collection<GameConstruction> getPending(String player);

}
