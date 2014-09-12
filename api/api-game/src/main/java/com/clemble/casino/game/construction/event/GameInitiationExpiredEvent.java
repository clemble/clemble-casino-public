package com.clemble.casino.game.construction.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 9/12/14.
 */
@JsonTypeName("initiationExpired")
public class GameInitiationExpiredEvent implements GameConstructionEvent {

    final private String sessionKey;

    @JsonCreator
    public GameInitiationExpiredEvent(@JsonProperty(SESSION_KEY) String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameInitiationExpiredEvent that = (GameInitiationExpiredEvent) o;

        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sessionKey != null ? sessionKey.hashCode() : 0;
    }

}
