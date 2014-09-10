package com.clemble.casino.game.event;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.game.construction.GameInitiation;
import com.clemble.casino.game.construction.GameInitiationAware;

abstract public class GameInitiationEvent extends GameManagementEvent implements GameInitiationAware {

    /**
     * Generated 04/01/14
     */
    private static final long serialVersionUID = -1145120622905552817L;

    final private GameInitiation initiation;

    public GameInitiationEvent(String sessionKey, GameInitiation initiation) {
        super(sessionKey);
        this.initiation = checkNotNull(initiation);
    }

    @Override
    public GameInitiation getInitiation(){
        return initiation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((initiation == null) ? 0 : initiation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameInitiationEvent other = (GameInitiationEvent) obj;
        if (initiation == null) {
            if (other.initiation != null)
                return false;
        } else if (!initiation.equals(other.initiation))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return initiation.toString();
    }

}
