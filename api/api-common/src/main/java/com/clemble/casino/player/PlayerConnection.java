package com.clemble.casino.player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Anton Oparin (antono@clemble.com)
 */
public class PlayerConnection implements PlayerAware {

    final private String player;
    final private String name;

    @JsonCreator
    public PlayerConnection(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("name") String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerConnection)) return false;

        PlayerConnection that = (PlayerConnection) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return player + ":" + name;
    }

}
