package com.clemble.casino.game.service;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.GameContext;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.event.GameManagementEvent;

public interface GameActionService extends ClembleService {

    public GameState getState(String sessionKey);

    public GameContext<?> getContext(String sessionKey);

    public GameManagementEvent process(String sessionKey, Event move);

}
