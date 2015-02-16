package com.clemble.casino.registration;

import java.io.Serializable;

import com.clemble.casino.security.ClembleConsumerDetails;
import com.clemble.casino.security.ClembleConsumerDetailsAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerLoginRequest implements PlayerCredentialAware, Serializable {

    private static final long serialVersionUID = -5259569180816587376L;

    final private PlayerCredential playerCredential;

    @JsonCreator
    public PlayerLoginRequest(
        @JsonProperty("playerCredential") final PlayerCredential playerCredential) {
        this.playerCredential = playerCredential;
    }

    @Override
    final public PlayerCredential getPlayerCredential() {
        return playerCredential;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((playerCredential == null) ? 0 : playerCredential.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerLoginRequest other = (PlayerLoginRequest) obj;
        if (playerCredential == null) {
            if (other.playerCredential != null)
                return false;
        } else if (!playerCredential.equals(other.playerCredential))
            return false;
        return true;
    }

}
