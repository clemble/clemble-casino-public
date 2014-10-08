package com.clemble.casino.game.lifecycle.construction.service;

import com.clemble.casino.game.lifecycle.construction.AutomaticGameRequest;
import com.clemble.casino.game.lifecycle.construction.GameConstruction;

import java.util.Collection;

public interface AutoGameConstructionService extends GameConstructionService<AutomaticGameRequest> {

    @Override
    GameConstruction construct(final AutomaticGameRequest request);

    @Override
    Collection<GameConstruction> getPending(String player);

}
