package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSession;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.GameState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameStateManagementEvent<State extends GameState> extends GameManagementEvent {

    /**
     * Generated 07/05/13
     */
    private static final long serialVersionUID = -4837244615682915463L;

    final private State state;

    public GameStateManagementEvent(GameSession<State> session) {
        super(session.getSession());
        this.state = session.getState();
    }

    @JsonCreator
    public GameStateManagementEvent(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("state") State state) {
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
        GameStateManagementEvent<?> other = (GameStateManagementEvent<?>) obj;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }


}
