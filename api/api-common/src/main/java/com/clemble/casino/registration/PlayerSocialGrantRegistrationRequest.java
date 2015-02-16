package com.clemble.casino.registration;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.social.SocialAccessGrant;
import com.clemble.casino.social.SocialAccessGrantAware;
import com.clemble.casino.security.ClembleConsumerDetails;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerSocialGrantRegistrationRequest
    extends PlayerLoginRequest
    implements SocialAccessGrantAware {

    private static final long serialVersionUID = -8802470944397014969L;

    final private SocialAccessGrant accessGrant;

    @JsonCreator
    public PlayerSocialGrantRegistrationRequest(
            @JsonProperty("playerCredential") final PlayerCredential playerCredential,
            @JsonProperty("accessGrant") final SocialAccessGrant accessGrant) {
        super(playerCredential);
        this.accessGrant = checkNotNull(accessGrant);
    }

    @Override
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
