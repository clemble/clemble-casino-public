package com.clemble.casino.player.event;

import com.clemble.casino.player.PlayerAware;

abstract public class PlayerConnectionEvent implements PlayerAware {

    /**
     * Generated 18/12/13
     */
    private static final long serialVersionUID = 2511615556814286244L;

    final private String player;
    final private String requester;

    public PlayerConnectionEvent(String player, String requester) {
        this.player = player;
        this.requester = requester;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getRequester() {
        return requester;
    }


}
