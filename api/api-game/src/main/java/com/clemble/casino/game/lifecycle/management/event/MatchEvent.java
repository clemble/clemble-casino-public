package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.MatchGameContext;

abstract public class MatchEvent implements GameManagementEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 1L;

    final private String sessionKey;
    final private MatchGameContext context;

    public MatchEvent(String sessionKey, MatchGameContext context) {
        this.sessionKey = sessionKey;
        this.context = context;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    public MatchGameContext getContext() {
        return context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchEvent that = (MatchEvent) o;

        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey != null ? sessionKey.hashCode() : 0;
        result = 31 * result + (context != null ? context.hashCode() : 0);
        return result;
    }
}
