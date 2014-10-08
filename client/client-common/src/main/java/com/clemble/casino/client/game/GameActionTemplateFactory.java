package com.clemble.casino.client.game;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.game.lifecycle.management.GameState;
import com.clemble.casino.game.lifecycle.management.service.GameActionService;

public class GameActionTemplateFactory implements GameActionOperationsFactory {

    final private String player;
    final private GameActionService actionService;
    final private EventListenerOperations eventListenerOperation;

    public GameActionTemplateFactory(String player, EventListenerOperations eventListenerOperation, GameActionService actionService) {
        this.player = checkNotNull(player);
        this.actionService = checkNotNull(actionService);
        this.eventListenerOperation = checkNotNull(eventListenerOperation);
    }

    @Override
    public <State extends GameState> GameActionOperations<State> construct(String session) {
        // Step 1. Constructing appropriate GameAction service
        return new GameActionTemplate<State>(player, session, eventListenerOperation, (GameActionService) actionService);
    }

}
