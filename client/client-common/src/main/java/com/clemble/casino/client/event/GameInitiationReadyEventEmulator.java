package com.clemble.casino.client.event;

import com.clemble.casino.client.game.GameInitiationOperations;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.event.server.GameInitiatedEvent;

public class GameInitiationReadyEventEmulator implements EventListener<GameInitiatedEvent>{

    final private GameInitiationOperations initiationOperations;

    public GameInitiationReadyEventEmulator(GameInitiationOperations initiationOperations) {
        this.initiationOperations = initiationOperations;
    }

    @Override
    public void onEvent(GameInitiatedEvent event) {
        GameSessionKey sessionKey = event.getSession();
        // Step 1. Automatically send initiation confirmation
        initiationOperations.confirm(sessionKey);
    }

}
