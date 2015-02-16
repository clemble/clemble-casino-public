package com.clemble.casino.registration;

import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.player.PlayerProfileAware;
import com.clemble.casino.security.ClembleConsumerDetails;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerRegistrationRequest
    extends PlayerLoginRequest
    implements PlayerProfileAware {

    private static final long serialVersionUID = -7419091879616342442L;

    final private PlayerProfile playerProfile;

    @JsonCreator
    public PlayerRegistrationRequest(
            @JsonProperty("playerCredential") PlayerCredential playerCredential,
            @JsonProperty("playerProfile") PlayerProfile playerProfile) {
        super(playerCredential);
        this.playerProfile = playerProfile;
    }

    final public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((playerProfile == null) ? 0 : playerProfile.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerRegistrationRequest other = (PlayerRegistrationRequest) obj;
        if (playerProfile == null) {
            if (other.playerProfile != null)
                return false;
        } else if (!playerProfile.equals(other.playerProfile))
            return false;
        return true;
    }

}
