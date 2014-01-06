package com.clemble.casino.game.service;

import java.util.Collection;

import com.clemble.casino.game.Game;
import com.clemble.casino.game.construct.GameInitiation;

public interface GameInitiationService {

    public Collection<GameInitiation> pending(Game game, String player);

    public GameInitiation ready(Game game, String sessionId, String player);

}
