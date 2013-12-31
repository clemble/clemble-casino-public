package com.clemble.casino.client.event;

import java.util.HashMap;
import java.util.Map;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.event.server.GameEndedEvent;
import com.clemble.casino.game.event.server.GameStateChangedEvent;
import com.clemble.casino.game.event.server.GameStateManagementEvent;
import com.clemble.casino.game.unit.GameUnit;

public class GameUnitEventEmulator implements EventListener<GameStateManagementEvent<?>>, EventSelector {

    final private EventListenerOperations listenerOperations;
    final private EventSelector selector = new EventTypeSelector(GameStateManagementEvent.class);
    final private Map<GameSessionKey, GameUnitWrapper<GameUnit>> sessionToUnitWrapper = new HashMap<GameSessionKey, GameUnitWrapper<GameUnit>>();

    public GameUnitEventEmulator(EventListenerOperations listenerOperations) {
        this.listenerOperations = listenerOperations;
    }

    @Override
    public void onEvent(GameStateManagementEvent<?> smEvent) {
        // Step 1. Processing state root
        GameSessionKey session = smEvent.getSession();
        GameUnitWrapper<GameUnit> unitWrap = sessionToUnitWrapper.get(session);
        if (unitWrap == null) {
            sessionToUnitWrapper.put(session, new GameUnitWrapper<GameUnit>(smEvent.getState().getRoot()));
            unitWrap = sessionToUnitWrapper.get(session);;
        }
        // Step 2. Game Session
        if (smEvent instanceof GameStateChangedEvent) {
            listenerOperations.update(sessionToUnitWrapper.get(session).set(smEvent.getState().getRoot()));
        }
        // Step 3. Removing unit wrapper for Game ended event
        if (smEvent instanceof GameEndedEvent)
            sessionToUnitWrapper.remove(session);
    }

    @Override
    public boolean filter(Event event) {
        return selector.filter(event);
    }

}
