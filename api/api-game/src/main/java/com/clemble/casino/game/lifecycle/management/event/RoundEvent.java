package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.RoundGameState;

abstract public class RoundEvent<State extends RoundGameState> extends GameManagementEvent {

    /**
     * Generated 07/05/13
     */
    private static final long serialVersionUID = -4837244615682915463L;

    final private State state;

    public RoundEvent(String sessionKey, State state) {
        super(sessionKey);
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((state == null) ? 0 : state.hashCode());
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
        RoundEvent other = (RoundEvent) obj;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }


}
