package com.clemble.casino.registration;

import com.clemble.casino.social.SocialConnectionData;
import com.clemble.casino.social.SocialConnectionDataAware;
import com.clemble.casino.security.ClembleConsumerDetails;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerSocialRegistrationRequest
    extends PlayerLoginRequest
    implements SocialConnectionDataAware {

    private static final long serialVersionUID = 709897454794810031L;

    final private SocialConnectionData socialConnectionData;

    @JsonCreator
    public PlayerSocialRegistrationRequest(
            @JsonProperty("consumerDetails") ClembleConsumerDetails consumerDetails,
            @JsonProperty("playerCredential") PlayerCredential playerCredential,
            @JsonProperty("socialConnectionData") SocialConnectionData socialConnectionData) {
        super(consumerDetails, playerCredential);
        this.socialConnectionData = socialConnectionData;
    }

    @Override
    public SocialConnectionData getSocialConnectionData() {
        return socialConnectionData;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((socialConnectionData == null) ? 0 : socialConnectionData.hashCode());
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
        PlayerSocialRegistrationRequest other = (PlayerSocialRegistrationRequest) obj;
        if (socialConnectionData == null) {
            if (other.socialConnectionData != null)
                return false;
        } else if (!socialConnectionData.equals(other.socialConnectionData))
            return false;
        return true;
    }

}
