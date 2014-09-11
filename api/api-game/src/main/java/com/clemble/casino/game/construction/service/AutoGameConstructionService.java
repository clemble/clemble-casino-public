package com.clemble.casino.game.construction.service;

import com.clemble.casino.game.construction.AutomaticGameRequest;
import com.clemble.casino.game.construction.GameConstruction;

import java.util.Collection;

public interface AutoGameConstructionService extends GameConstructionService<AutomaticGameRequest> {

    @Override
    GameConstruction construct(final AutomaticGameRequest request);

    @Override
    Collection<GameConstruction> getPending(String player);

}
