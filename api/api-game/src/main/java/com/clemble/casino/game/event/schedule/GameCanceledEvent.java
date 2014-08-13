package com.clemble.casino.game.event.schedule;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.event.GameConstructionEvent;
import com.clemble.casino.game.GameSessionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("canceled")
public class GameCanceledEvent implements GameConstructionEvent {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 1L;

    final private GameSessionKey session;

    @JsonCreator
    public GameCanceledEvent(@JsonProperty(GameSessionAware.SESSION_KEY) GameSessionKey session) {
        this.session = session;
    }

    @Override
    public GameSessionKey getSessionKey() {
        return session;
    }

    @Override
    public int hashCode() {
        return session.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return session.equals(((GameCanceledEvent) obj).session);
    }

    @Override
    public String toString() {
        return "canceled:" + session;
    }

}
