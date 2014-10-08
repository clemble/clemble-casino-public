package com.clemble.casino.client.event;

import com.clemble.casino.ActionLatch;
import com.clemble.casino.event.action.PlayerExpectedAction;
import com.clemble.casino.event.Event;
import com.clemble.casino.game.lifecycle.management.event.RoundChangedEvent;
import com.clemble.casino.game.lifecycle.management.event.RoundEvent;
import com.clemble.casino.game.lifecycle.management.event.RoundStartedEvent;

public class PlayerToMoveEventEmulator implements EventListener<RoundEvent>, EventSelector {

    final private String player;
    final private EventListenerOperations listenerOperations;

    public PlayerToMoveEventEmulator(String player, EventListenerOperations listenerOperations) {
        this.player = player;
        this.listenerOperations = listenerOperations;
    }

    @Override
    public void onEvent(RoundEvent smEvent) {
        // Step 1. Checking Action latch
        String sessionKey = smEvent.getSessionKey();
        if (smEvent.getState() != null
                && smEvent.getState().getContext() != null
                && smEvent.getState().getContext().getActionLatch() != null) {
            ActionLatch actionLatch = smEvent.getState().getContext().getActionLatch();
            for (String participant : actionLatch.fetchParticipants())
                if (!actionLatch.acted(participant)) {
                    PlayerExpectedAction expectedEvent = (PlayerExpectedAction) actionLatch.filterAction(participant);
                    listenerOperations.update(new GamePlayerToMoveEvent(sessionKey, participant, expectedEvent.getAction(), player.equals(participant)));
                }
        }
    }

    @Override
    public boolean filter(Event event) {
        return (event instanceof RoundStartedEvent) || (event instanceof RoundChangedEvent);
    };

}
