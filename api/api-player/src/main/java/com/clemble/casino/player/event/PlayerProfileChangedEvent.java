package com.clemble.casino.player.event;

import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.player.PlayerProfileAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/12/14.
 */
@JsonTypeName(PlayerProfileChangedEvent.JSON_TYPE)
public class PlayerProfileChangedEvent implements PlayerEvent, PlayerProfileAware {

    final public static String JSON_TYPE = "player:profile:changed";

    final private String player;
    final private PlayerProfile playerProfile;

    @JsonCreator
    public PlayerProfileChangedEvent(@JsonProperty(PLAYER) String player, @JsonProperty("profile") PlayerProfile playerProfile) {
        this.player = player;
        this.playerProfile = playerProfile;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerProfileChangedEvent that = (PlayerProfileChangedEvent) o;

        if (!player.equals(that.player)) return false;
        return playerProfile.equals(that.playerProfile);

    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + playerProfile.hashCode();
        return result;
    }

}
