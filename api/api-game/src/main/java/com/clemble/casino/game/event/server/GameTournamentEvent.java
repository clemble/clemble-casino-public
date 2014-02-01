package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;

abstract public class GameTournamentEvent extends GameManagementEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 7969173510429346971L;

    public GameTournamentEvent(GameSessionKey sessionKey) {
        super(sessionKey);
    }

}
