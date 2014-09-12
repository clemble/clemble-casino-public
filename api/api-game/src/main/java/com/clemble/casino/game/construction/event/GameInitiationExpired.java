package com.clemble.casino.game.construction.event;

import com.clemble.casino.event.GameEvent;

/**
 * Created by mavarazy on 9/12/14.
 */
public class GameInitiationExpired implements GameConstructionEvent {

    final private String sessionKey;

    public GameInitiationExpired(String sessionKey) {
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

        GameInitiationExpired that = (GameInitiationExpired) o;

        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sessionKey != null ? sessionKey.hashCode() : 0;
    }

}
