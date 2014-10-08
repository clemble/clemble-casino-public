package com.clemble.casino.lifecycle.management.event.action.surrender;

import com.clemble.casino.lifecycle.management.event.action.PlayerAction;

abstract public class SurrenderAction implements PlayerAction {

    /**
     * Generated 10/06/2013
     */
    private static final long serialVersionUID = 4875966049653785672L;

    final private String player;

    public SurrenderAction(String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SurrenderAction)) return false;

        SurrenderAction that = (SurrenderAction) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return player != null ? player.hashCode() : 0;
    }
}
