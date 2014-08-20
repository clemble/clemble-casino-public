package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.GameSessionAware;

public class GameSessionEventSelector implements EventSelector {

    final private String sessionKey;

    public GameSessionEventSelector(String sessionId) {
        this.sessionKey = sessionId;
    }

    @Override
    public boolean filter(Event event) {
        return event instanceof GameSessionAware ? ((GameSessionAware) event).getSessionKey().equals(sessionKey) : false;
    }

}
