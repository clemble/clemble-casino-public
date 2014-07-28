package com.clemble.casino.player.web;

import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.player.PlayerProfileAware;
import com.clemble.casino.player.security.PlayerCredential;
import com.clemble.casino.player.security.PlayerCredentialAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PlayerBaseRegistrationRequest implements PlayerCredentialAware, PlayerProfileAware, Serializable {

    final private PlayerCredential playerCredential;
    final private PlayerProfile playerProfile;

    @JsonCreator
    public PlayerBaseRegistrationRequest(
        @JsonProperty("playerCredential") PlayerCredential playerCredential,
        @JsonProperty("playerProfile") PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
        this.playerCredential = playerCredential;
    }

    @Override
    public PlayerCredential getPlayerCredential() {
        return playerCredential;
    }

    @Override
    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerBaseRegistrationRequest that = (PlayerBaseRegistrationRequest) o;

        if (playerCredential != null ? !playerCredential.equals(that.playerCredential) : that.playerCredential != null)
            return false;
        if (playerProfile != null ? !playerProfile.equals(that.playerProfile) : that.playerProfile != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerCredential != null ? playerCredential.hashCode() : 0;
        result = 31 * result + (playerProfile != null ? playerProfile.hashCode() : 0);
        return result;
    }
}
