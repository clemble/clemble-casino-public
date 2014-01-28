package com.clemble.casino.game;

import java.util.List;

public class PotGameSession {

    private List<GameSession<?>> sessions;

    public List<GameSession<?>> getSessions() {
        return sessions;
    }

    public void setSessions(List<GameSession<?>> sessions) {
        this.sessions = sessions;
    }

}
