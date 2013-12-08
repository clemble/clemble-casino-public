package com.clemble.casino.player.web;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.player.SocialAccessGrant;
import com.clemble.casino.player.client.ClembleConsumerDetails;
import com.clemble.casino.player.security.PlayerCredential;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerSocialGrantRegistrationRequest extends PlayerLoginRequest {

    private static final long serialVersionUID = -8802470944397014969L;

    final private SocialAccessGrant accessGrant;

    @JsonCreator
    public PlayerSocialGrantRegistrationRequest(@JsonProperty("consumerDetails") final ClembleConsumerDetails consumerDetails,
            @JsonProperty("playerCredential") final PlayerCredential playerCredential, @JsonProperty("accessGrant") SocialAccessGrant accessGrant) {
        super(consumerDetails, playerCredential);
        this.accessGrant = checkNotNull(accessGrant);
    }

    public SocialAccessGrant getAccessGrant() {
        return accessGrant;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessGrant == null) ? 0 : accessGrant.hashCode());
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
        PlayerSocialGrantRegistrationRequest other = (PlayerSocialGrantRegistrationRequest) obj;
        if (accessGrant == null) {
            if (other.accessGrant != null)
                return false;
        } else if (!accessGrant.equals(other.accessGrant))
            return false;
        return true;
    }

}
