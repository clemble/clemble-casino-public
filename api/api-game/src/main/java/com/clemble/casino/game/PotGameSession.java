package com.clemble.casino.game;

import java.util.List;

public class PotGameSession implements GameSessionAware {

    /**
     * Generated 28/01/14
     */
    private static final long serialVersionUID = 2681729492829267205L;

    private GameSessionKey sessionKey;
    private List<GameSession<?>> sessions;

    public List<GameSession<?>> getSessions() {
        return sessions;
    }

    public void setSessions(List<GameSession<?>> sessions) {
        this.sessions = sessions;
    }

    @Override
    public GameSessionKey getSession() {
        return sessionKey;
    }

    public void setSession(GameSessionKey sessionKey) {
        this.sessionKey = sessionKey;
    }

}
