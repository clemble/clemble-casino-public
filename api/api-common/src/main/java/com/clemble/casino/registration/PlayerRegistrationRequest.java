package com.clemble.casino.registration;

import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.player.PlayerProfileAware;
import com.clemble.casino.security.ClembleConsumerDetails;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

public class PlayerRegistrationRequest
    implements PlayerProfileAware, PlayerCredentialAware {

    private static final long serialVersionUID = -7419091879616342442L;

    final private PlayerCredential playerCredential;
    final private PlayerProfile playerProfile;

    @JsonCreator
    public PlayerRegistrationRequest(
            @JsonProperty("playerCredential") PlayerCredential playerCredential,
            @JsonProperty("playerProfile") PlayerProfile playerProfile) {
        this.playerCredential = playerCredential;
        this.playerProfile = playerProfile;
    }

    @Override
    @Valid
    public PlayerCredential getPlayerCredential() {
        return playerCredential;
    }

    @Override
    @Valid
    final public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerRegistrationRequest)) return false;

        PlayerRegistrationRequest that = (PlayerRegistrationRequest) o;

        if (!playerCredential.equals(that.playerCredential)) return false;
        if (!playerProfile.equals(that.playerProfile)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerCredential.hashCode();
        result = 31 * result + playerProfile.hashCode();
        return result;
    }

}
