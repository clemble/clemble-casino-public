package com.clemble.casino.game.construction;

import java.util.Collection;

public interface AutoGameConstructionService extends GameConstructionService<AutomaticGameRequest> {

    @Override
    GameConstruction construct(final AutomaticGameRequest request);

    @Override
    Collection<GameConstruction> getPending(String player);

}
