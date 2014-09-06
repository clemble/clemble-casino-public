package com.clemble.casino.game.action.management;

import com.clemble.casino.game.action.ClientGameEvent;


/**
 * Created by mavarazy on 23/12/13.
 */
public class GameManagementEvent implements ClientGameEvent {

    /**
     * Generated 02/01/2012
     */
    private static final long serialVersionUID = -3237359155760508649L;

    final private String player;

    public GameManagementEvent(String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

}
