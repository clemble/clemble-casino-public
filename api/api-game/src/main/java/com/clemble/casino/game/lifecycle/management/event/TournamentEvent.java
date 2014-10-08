package com.clemble.casino.game.lifecycle.management.event;

abstract public class TournamentEvent implements GameManagementEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 7969173510429346971L;

    final private String sessionKey;

    public TournamentEvent(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionKey(){
        return sessionKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TournamentEvent that = (TournamentEvent) o;

        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sessionKey != null ? sessionKey.hashCode() : 0;
    }
}
