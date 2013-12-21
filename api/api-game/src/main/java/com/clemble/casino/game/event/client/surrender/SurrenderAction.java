package com.clemble.casino.game.event.client.surrender;

import com.clemble.casino.game.event.client.GameAction;

abstract public class SurrenderAction extends GameAction {

    /**
     * Generated 10/06/2013
     */
    private static final long serialVersionUID = 4875966049653785672L;

    public SurrenderAction(String player) {
        super(player);
    }

}
