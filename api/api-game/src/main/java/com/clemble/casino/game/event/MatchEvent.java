package com.clemble.casino.game.event;

import com.clemble.casino.game.MatchGameContext;

abstract public class MatchEvent extends GameManagementEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 1L;
    
    final private MatchGameContext context;

    public MatchEvent(String sessionKey, MatchGameContext context) {
        super(sessionKey);
        this.context = context;
    }

    public MatchGameContext getContext() {
        return context;
    }

}
