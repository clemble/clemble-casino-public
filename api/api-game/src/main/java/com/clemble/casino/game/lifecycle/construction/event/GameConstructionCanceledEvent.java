package com.clemble.casino.game.lifecycle.construction.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:construction:canceled")
public class GameConstructionCanceledEvent implements GameConstructionEvent {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 1L;

    final private String session;

    @JsonCreator
    public GameConstructionCanceledEvent(@JsonProperty(SESSION_KEY) String session) {
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
        return session.equals(((GameConstructionCanceledEvent) obj).session);
    }

    @Override
    public String toString() {
        return "canceled:" + session;
    }

}
