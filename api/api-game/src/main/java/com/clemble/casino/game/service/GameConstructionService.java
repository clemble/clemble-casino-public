package com.clemble.casino.game.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameConstructionRequest;

public interface GameConstructionService<T extends GameConstructionRequest> extends ClembleService {

    public GameConstruction construct(T request);

}
