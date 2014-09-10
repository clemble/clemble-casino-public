package com.clemble.casino.game.event;

import com.clemble.casino.game.construction.event.GameConstructionEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("canceled")
public class GameCanceledEvent implements GameConstructionEvent {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 1L;

    final private String session;

    @JsonCreator
    public GameCanceledEvent(@JsonProperty(SESSION_KEY) String session) {
        this.session = session;
    }

    @Override
    public String getSessionKey() {
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
