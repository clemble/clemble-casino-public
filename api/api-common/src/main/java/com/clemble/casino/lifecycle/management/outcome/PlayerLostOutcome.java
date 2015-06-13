package com.clemble.casino.lifecycle.management.outcome;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 16/10/14.
 */
public class PlayerLostOutcome extends Outcome implements PlayerAware {

    final private String player;

    @JsonCreator
    public PlayerLostOutcome(@JsonProperty("player") String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerLostOutcome)) return false;
        if (!super.equals(o)) return false;

        PlayerLostOutcome that = (PlayerLostOutcome) o;

        return !(player != null ? !player.equals(that.player) : that.player != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (player != null ? player.hashCode() : 0);
        return result;
    }

}
