package com.clemble.casino.client.event;

import com.clemble.casino.game.construction.event.GameInitiationCreatedEvent;
import com.clemble.casino.game.construction.service.GameInitiationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameInitiationReadyEventEmulator implements EventListener<GameInitiationCreatedEvent>{

    final private Logger LOG = LoggerFactory.getLogger(GameInitiationReadyEventEmulator.class);
    final private GameInitiationService initiationService;

    public GameInitiationReadyEventEmulator(GameInitiationService initiationService) {
        this.initiationService = initiationService;
    }

    @Override
    public void onEvent(GameInitiationCreatedEvent event) {
        String sessionKey = event.getSessionKey();
        // Step 1. Automatically send initiation confirmation
        initiationService.confirm(sessionKey);
    }

}
