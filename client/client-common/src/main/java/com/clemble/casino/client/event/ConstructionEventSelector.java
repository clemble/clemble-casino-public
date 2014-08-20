package com.clemble.casino.client.event;

import com.clemble.casino.game.event.GameConstructionEvent;
import com.clemble.casino.event.Event;
import com.clemble.casino.game.GameSessionAware;

public class ConstructionEventSelector implements EventSelector, GameSessionAware {

    /**
     * Generated 29/11/13
     */
    private static final long serialVersionUID = 4072930365311184802L;

    final private String sessionKey;

    public ConstructionEventSelector(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public boolean filter(Event event) {
        return event instanceof GameConstructionEvent && ((GameConstructionEvent) event).getSessionKey().equals(sessionKey);
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

}
