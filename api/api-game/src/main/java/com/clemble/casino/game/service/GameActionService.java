package com.clemble.casino.game.service;

import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.action.MadeMove;

public interface GameActionService<State extends GameState> {

    public State getState(Game game, String session);

    public State process(Game game, String sessionId, GameAction move);

    public MadeMove getAction(Game game, String sessionId, int actionId);

}
