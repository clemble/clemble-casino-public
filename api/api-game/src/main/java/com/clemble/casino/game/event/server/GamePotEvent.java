package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;

abstract public class GamePotEvent extends GameManagementEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 1L;

    public GamePotEvent(GameSessionKey sessionKey) {
        super(sessionKey);
    }

}
