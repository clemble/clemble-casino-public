package com.clemble.casino.client.game;

import com.clemble.casino.game.GameState;

public interface GameActionOperationsFactory {

    public <State extends GameState> GameActionOperations<State> construct(String sessionKey);

}
