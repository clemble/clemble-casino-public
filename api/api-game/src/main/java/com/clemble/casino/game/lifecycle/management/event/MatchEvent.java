package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.MatchGameContext;

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