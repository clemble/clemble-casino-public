package com.clemble.casino.client.event;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Map;

import com.clemble.casino.client.game.GameConstructionOperations;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.event.server.GameInitiatedEvent;

public class GameInitiationReadyEventEmulator implements EventListener<GameInitiatedEvent>{
    
    final private Map<Game, GameConstructionOperations<?>> gameToConstructionOperations;
    
    public GameInitiationReadyEventEmulator(Map<Game, GameConstructionOperations<?>> gameToConstructionOperations) {
        this.gameToConstructionOperations = checkNotNull(gameToConstructionOperations);
    }

    @Override
    public void onEvent(GameInitiatedEvent event) {
        GameSessionKey sessionKey = event.getSession();
        // Step 1. Automatically send initiation confirmation
        gameToConstructionOperations.get(sessionKey.getGame()).confirm(sessionKey.getSession());
    }

}
