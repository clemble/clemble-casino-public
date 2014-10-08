package com.clemble.casino.game.lifecycle.construction.service;

import com.clemble.casino.lifecycle.construction.service.ConstructionService;
import com.clemble.casino.game.lifecycle.configuration.GameConfiguration;
import com.clemble.casino.game.lifecycle.construction.GameConstruction;
import com.clemble.casino.game.lifecycle.construction.GameConstructionRequest;

import java.util.Collection;

public interface GameConstructionService<T extends GameConstructionRequest> extends ConstructionService<GameConfiguration, T> {

    GameConstruction construct(T request);

    Collection<GameConstruction> getPending(String player);

}
