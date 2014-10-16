package com.clemble.casino.lifecycle.management.outcome;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO make PlayerAware
public class PlayerWonOutcome extends Outcome implements PlayerAware {

    /**
     * Generated 22/01/14
     */
    private static final long serialVersionUID = -3259192146118069428L;

    final private String player;

    @JsonCreator
    public PlayerWonOutcome(@JsonProperty("player") String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public int hashCode() {
        return player == null ? 0 : player.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerWonOutcome other = (PlayerWonOutcome) obj;
        return player.equals(other.player);
    }

    @Override
    public String toString(){
        return "won:" + player;
    }
}
