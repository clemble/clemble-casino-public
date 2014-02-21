package com.clemble.casino.game;

import com.clemble.casino.game.specification.GameConfigurationKey;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("tournament")
public class TournamentGameRecord implements GameRecord {

    /**
     * Generated 29/01/14
     */
    private static final long serialVersionUID = 3764146624852413539L;

    private GameConfigurationKey configurationKey;
    private GameSessionKey sessionKey;
    private GameSessionState sessionState;

    @Override
    public GameConfigurationKey getConfigurationKey() {
        return configurationKey;
    }

    public void setConfigurationKey(GameConfigurationKey configurationKey) {
        this.configurationKey = configurationKey;
    }

    @Override
    public GameSessionKey getSession() {
        return sessionKey;
    }

    public void setSession(GameSessionKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public GameSessionState getSessionState() {
        return sessionState;
    }

    public void setSessionState(GameSessionState sessionState) {
        this.sessionState = sessionState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TournamentGameRecord that = (TournamentGameRecord) o;

        if (configurationKey != null ? !configurationKey.equals(that.configurationKey) : that.configurationKey != null)
            return false;
        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;
        if (sessionState != that.sessionState) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = configurationKey != null ? configurationKey.hashCode() : 0;
        result = 31 * result + (sessionKey != null ? sessionKey.hashCode() : 0);
        result = 31 * result + (sessionState != null ? sessionState.hashCode() : 0);
        return result;
    }
}
