package com.clemble.casino.game.lifecycle.management.event;

abstract public class TournamentEvent extends GameManagementEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 7969173510429346971L;

    public TournamentEvent(String sessionKey) {
        super(sessionKey);
    }

}
