package com.clemble.casino.game.event.server;

import java.io.Serializable;
import com.clemble.casino.game.event.GameSessionAwareEvent;

abstract public class GameManagementEvent implements GameSessionAwareEvent, Serializable {

    /**
     * Generated 07/05/13
     */
    private static final long serialVersionUID = -4837244615682915463L;

    final private String sessionKey;

    public GameManagementEvent(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sessionKey == null) ? 0 : sessionKey.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameManagementEvent other = (GameManagementEvent) obj;
        if (sessionKey == null) {
            if (other.sessionKey != null)
                return false;
        } else if (!sessionKey.equals(other.sessionKey))
            return false;
        return true;
    }

}
