package com.clemble.casino.game.lifecycle.management.service;

import com.clemble.casino.game.lifecycle.management.GameContext;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.lifecycle.management.GameState;
import com.clemble.casino.game.lifecycle.management.event.GameManagementEvent;
import com.clemble.casino.lifecycle.management.event.action.Action;
import com.clemble.casino.lifecycle.management.service.ActionService;

public interface GameActionService extends ClembleService, ActionService {

    @Override
    public GameState getState(String sessionKey);

    @Override
    public GameManagementEvent process(String sessionKey, Action action);

    public GameContext<?> getContext(String sessionKey);

}
