package com.clemble.casino.game.event.server;

import com.clemble.casino.game.RoundGameRecord;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.GameState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoundEvent extends GameManagementEvent {

    /**
     * Generated 07/05/13
     */
    private static final long serialVersionUID = -4837244615682915463L;

    final private GameState state;

    public RoundEvent(RoundGameRecord session) {
        super(session.getSession());
        this.state = session.getState();
    }

    @JsonCreator
    public RoundEvent(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("state") GameState state) {
        super(sessionKey);
        this.state = state;
    }

    public GameState getState() {
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
