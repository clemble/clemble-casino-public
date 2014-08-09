package com.clemble.casino.game.service;

import com.clemble.casino.game.construct.AutomaticGameRequest;
import com.clemble.casino.game.construct.GameConstruction;

public interface AutoGameConstructionService extends GameConstructionService<AutomaticGameRequest> {

    public GameConstruction construct(final AutomaticGameRequest request);

}
