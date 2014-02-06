package com.clemble.casino.client.event;

import com.clemble.casino.base.ActionLatch;
import com.clemble.casino.base.ExpectedEvent;
import com.clemble.casino.event.Event;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.event.server.GameMatchEvent;
import com.clemble.casino.game.event.server.GameMatchStartedEvent;
import com.clemble.casino.game.event.server.GameMatchStateChangedEvent;

public class PlayerToMoveEventEmulator implements EventListener<GameMatchEvent>, EventSelector {

    final private String player;
    final private EventListenerOperations listenerOperations;

    public PlayerToMoveEventEmulator(String player, EventListenerOperations listenerOperations) {
        this.player = player;
        this.listenerOperations = listenerOperations;
    }

    @Override
    public void onEvent(GameMatchEvent smEvent) {
        // Step 1. Checking Action latch
        GameSessionKey sessionKey = smEvent.getSession();
        if (smEvent.getState() != null
                && smEvent.getState().getContext() != null
                && smEvent.getState().getContext().getActionLatch() != null) {
            ActionLatch actionLatch = smEvent.getState().getContext().getActionLatch();
            for (String participant : actionLatch.fetchParticipants())
                if (!actionLatch.acted(participant)) {
                    ExpectedEvent expectedEvent = (ExpectedEvent) actionLatch.fetchAction(participant);
                    listenerOperations.update(new PlayerToMoveEvent(sessionKey, participant, expectedEvent.getAction(), player.equals(participant)));
                }
        }
    }

    @Override
    public boolean filter(Event event) {
        return (event instanceof GameMatchStartedEvent) || (event instanceof GameMatchStateChangedEvent);
    };

}
