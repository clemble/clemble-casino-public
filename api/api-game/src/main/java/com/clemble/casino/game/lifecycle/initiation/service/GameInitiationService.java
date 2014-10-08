package com.clemble.casino.game.lifecycle.initiation.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.lifecycle.initiation.service.InitiationService;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;

import java.util.Collection;

public interface GameInitiationService extends InitiationService<GameInitiation>, ClembleService {

    GameInitiation confirm(String sessionKey);

    @Override
    Collection<GameInitiation> getPending();

}
