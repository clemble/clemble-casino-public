package com.clemble.casino.game.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameConstructionRequest;

import java.util.Collection;

public interface GameConstructionService<T extends GameConstructionRequest> extends ClembleService {

    GameConstruction construct(T request);

    Collection<GameConstruction> getPending(String player);

}
