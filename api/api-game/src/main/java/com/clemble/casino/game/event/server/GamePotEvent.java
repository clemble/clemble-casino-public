package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.PotGameContext;

abstract public class GamePotEvent extends GameManagementEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 1L;
    
    final private PotGameContext context;

    public GamePotEvent(GameSessionKey sessionKey, PotGameContext context) {
        super(sessionKey);
        this.context = context;
    }

    public PotGameContext getContext() {
        return context;
    }

}
