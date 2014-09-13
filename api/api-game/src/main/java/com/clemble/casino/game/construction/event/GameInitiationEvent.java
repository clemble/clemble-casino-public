package com.clemble.casino.game.construction.event;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.game.construction.GameInitiation;
import com.clemble.casino.game.construction.GameInitiationAware;
import com.clemble.casino.game.event.GameManagementEvent;

abstract public class GameInitiationEvent implements GameConstructionEvent, GameInitiationAware {

    /**
     * Generated 04/01/14
     */
    private static final long serialVersionUID = -1145120622905552817L;

    final private String sessionKey;
    final private GameInitiation initiation;

    public GameInitiationEvent(String sessionKey, GameInitiation initiation) {
        this.sessionKey = sessionKey;
        this.initiation = checkNotNull(initiation);
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public GameInitiation getInitiation(){
        return initiation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameInitiationEvent that = (GameInitiationEvent) o;

        if (!initiation.equals(that.initiation)) return false;
        if (!sessionKey.equals(that.sessionKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey.hashCode();
        result = 31 * result + initiation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return initiation.toString();
    }

}
