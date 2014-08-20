package com.clemble.casino.game.service;

import com.clemble.casino.game.Game;
import com.clemble.casino.game.construct.GameInitiation;

public interface GameInitiationService {

    public GameInitiation confirm(String sessionKey, String player);

}
