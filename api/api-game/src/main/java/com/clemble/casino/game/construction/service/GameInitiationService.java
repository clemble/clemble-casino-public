package com.clemble.casino.game.construction.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.construction.service.InitiationService;
import com.clemble.casino.game.construction.GameInitiation;

import java.util.Collection;

public interface GameInitiationService extends InitiationService<GameInitiation>, ClembleService {

    GameInitiation confirm(String sessionKey);

    @Override
    Collection<GameInitiation> getPending();

}
