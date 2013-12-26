package com.clemble.casino.game.event.server;

import java.io.Serializable;

import com.clemble.casino.event.GameEvent;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionKey;

abstract public class GameManagementEvent implements GameSessionAware, GameEvent, Serializable {

    /**
     * Generated 07/05/13
     */
    private static final long serialVersionUID = -4837244615682915463L;

    final private GameSessionKey session;

    public GameManagementEvent(GameSessionKey sessionKey) {
        this.session = sessionKey;
    }

    @Override
    public GameSessionKey getSession() {
        return session;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((session == null) ? 0 : session.hashCode());
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
        if (session == null) {
            if (other.session != null)
                return false;
        } else if (!session.equals(other.session))
            return false;
        return true;
    }

}
