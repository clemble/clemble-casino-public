package com.clemble.casino.registration;

import java.io.Serializable;

import com.clemble.casino.security.ClembleConsumerDetails;
import com.clemble.casino.security.ClembleConsumerDetailsAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerLoginRequest implements PlayerCredentialAware, ClembleConsumerDetailsAware, Serializable {

    private static final long serialVersionUID = -5259569180816587376L;

    final private PlayerCredential playerCredential;
    final private ClembleConsumerDetails consumerDetails;

    @JsonCreator
    public PlayerLoginRequest(
        @JsonProperty("consumerDetails") final ClembleConsumerDetails consumerDetails,
        @JsonProperty("playerCredential") final PlayerCredential playerCredential) {
        this.playerCredential = playerCredential;
        this.consumerDetails = consumerDetails;
    }

    @Override
    final public PlayerCredential getPlayerCredential() {
        return playerCredential;
    }

    @Override
    final public ClembleConsumerDetails getConsumerDetails() {
        return consumerDetails;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((consumerDetails == null) ? 0 : consumerDetails.hashCode());
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
        if (consumerDetails == null) {
            if (other.consumerDetails != null)
                return false;
        } else if (!consumerDetails.equals(other.consumerDetails))
            return false;
        if (playerCredential == null) {
            if (other.playerCredential != null)
                return false;
        } else if (!playerCredential.equals(other.playerCredential))
            return false;
        return true;
    }

}
