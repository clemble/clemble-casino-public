package com.clemble.casino.event.surrender;

import com.clemble.casino.event.PlayerAwareEvent;

abstract public class SurrenderEvent implements PlayerAwareEvent {

    /**
     * Generated 10/06/2013
     */
    private static final long serialVersionUID = 4875966049653785672L;

    final private String player;

    public SurrenderEvent(String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SurrenderEvent)) return false;

        SurrenderEvent that = (SurrenderEvent) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return player != null ? player.hashCode() : 0;
    }
}
