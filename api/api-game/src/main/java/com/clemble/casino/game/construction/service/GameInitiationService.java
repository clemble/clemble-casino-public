package com.clemble.casino.game.construction.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.construction.GameInitiation;

public interface GameInitiationService extends ClembleService {

    public GameInitiation confirm(String sessionKey);

}
