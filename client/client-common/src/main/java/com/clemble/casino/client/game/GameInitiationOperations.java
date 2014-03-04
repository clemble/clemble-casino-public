package com.clemble.casino.client.game;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.construct.GameInitiation;

public interface GameInitiationOperations {

    public GameInitiation confirm(GameSessionKey session);

}