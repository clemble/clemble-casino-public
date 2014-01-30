package com.clemble.casino.game.service;

import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameConstructionRequest;

public interface GameConstructionService<T extends GameConstructionRequest> {

    public GameConstruction construct(T request);

}
