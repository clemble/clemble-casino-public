package com.clemble.casino.search;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Anton Oparin (antono@clemble.com)
 */
public class PlayerSearch implements PlayerAware {

    final private String player;
    final private String fullName;

    @JsonCreator
    public PlayerSearch(
            @JsonProperty(PLAYER) String player,
            @JsonProperty("fullName") String fullName
    ) {
        this.player = player;
        this.fullName = fullName;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerSearch)) return false;

        PlayerSearch that = (PlayerSearch) o;

        if (!player.equals(that.player)) return false;
        return fullName.equals(that.fullName);
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

}
