package com.clemble.casino.game.construction;

import com.clemble.casino.construction.ConstructionService;
import com.clemble.casino.game.configuration.GameConfiguration;

import java.util.Collection;

public interface GameConstructionService<T extends GameConstructionRequest> extends ConstructionService<GameConfiguration, T> {

    GameConstruction construct(T request);

    Collection<GameConstruction> getPending(String player);

}
