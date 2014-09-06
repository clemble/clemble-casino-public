package com.clemble.casino.game.action.management;

public class LeaveGameSessionEvent extends GameManagementEvent {

    /**
     * Generated 02/01/2014
     */
    private static final long serialVersionUID = 8080326622761097006L;

    public LeaveGameSessionEvent(String player) {
        super(player);
    }

}
