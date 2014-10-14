package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.MatchGameState;

abstract public class MatchEvent implements GameManagementEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 1L;

    final private String sessionKey;
    final private MatchGameState state;

    public MatchEvent(String sessionKey, MatchGameState state) {
        this.sessionKey = sessionKey;
        this.state = state;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    public MatchGameState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchEvent that = (MatchEvent) o;

        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey != null ? sessionKey.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
