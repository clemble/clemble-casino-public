package com.clemble.casino.game.service;

import com.clemble.casino.game.GameContext;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.event.server.GameManagementEvent;

public interface GameActionService extends ClembleService {

    public GameState getState(Game game, String session);

    public GameContext<?> getContext(Game game, String session);

    public GameManagementEvent process(Game game, String session, GameAction move);

}
