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

}
