package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.GameSessionAware;

public class GameSessionEventSelector implements EventSelector {

    final private GameSessionKey session;

    public GameSessionEventSelector(GameSessionKey sessionId) {
        this.session = sessionId;
    }

    @Override
    public boolean filter(Event event) {
        return event instanceof GameSessionAware ? ((GameSessionAware) event).getSession().equals(session) : false;
    }

}
