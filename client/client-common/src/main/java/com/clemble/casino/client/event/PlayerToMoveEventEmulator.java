package com.clemble.casino.client.event;

import com.clemble.casino.base.ActionLatch;
import com.clemble.casino.event.Event;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.event.server.GameStartedEvent;
import com.clemble.casino.game.event.server.GameStateChangedEvent;
import com.clemble.casino.game.event.server.GameStateManagementEvent;

public class PlayerToMoveEventEmulator implements EventListener, EventSelector {

    final private String player;
    final private EventListenerOperations listenerOperations;

    public PlayerToMoveEventEmulator(String player, EventListenerOperations listenerOperations) {
        this.player = player;
        this.listenerOperations = listenerOperations;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void onEvent(Event event) {
        // Step 1. Casting to GameStateManagementEvent
        GameStateManagementEvent stateManagementEvent = ((GameStateManagementEvent) event);
        // Step 2. Checking Action latch
        GameSessionKey sessionKey = stateManagementEvent.getSession();
        if (stateManagementEvent.getState() != null
                && stateManagementEvent.getState().getContext() != null
                && stateManagementEvent.getState().getContext().getActionLatch() != null) {
            ActionLatch actionLatch = stateManagementEvent.getState().getContext().getActionLatch();
            for (String participant : actionLatch.fetchParticipants())
                if (!actionLatch.acted(participant))
                    listenerOperations.update(new PlayerToMoveEvent(sessionKey, participant, actionLatch.expectedClass(), player.equals(participant)));
        }
    }

    @Override
    public boolean filter(Event event) {
        return (event instanceof GameStartedEvent) || (event instanceof GameStateChangedEvent);
    };

}
