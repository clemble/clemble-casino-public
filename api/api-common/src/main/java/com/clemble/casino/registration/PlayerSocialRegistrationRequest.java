package com.clemble.casino.registration;

import com.clemble.casino.social.SocialConnectionData;
import com.clemble.casino.social.SocialConnectionDataAware;
import com.clemble.casino.security.ClembleConsumerDetails;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerSocialRegistrationRequest
    implements PlayerCredentialAware, SocialConnectionDataAware {

    private static final long serialVersionUID = 709897454794810031L;

    final private PlayerCredential playerCredential;
    final private SocialConnectionData socialConnectionData;

    @JsonCreator
    public PlayerSocialRegistrationRequest(
            @JsonProperty("playerCredential") PlayerCredential playerCredential,
            @JsonProperty("socialConnectionData") SocialConnectionData socialConnectionData) {
        this.playerCredential = playerCredential;
        this.socialConnectionData = socialConnectionData;
    }

    @Override
    public PlayerCredential getPlayerCredential() {
        return playerCredential;
    }

    @Override
    public SocialConnectionData getSocialConnectionData() {
        return socialConnectionData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerSocialRegistrationRequest)) return false;

        PlayerSocialRegistrationRequest that = (PlayerSocialRegistrationRequest) o;

        if (!playerCredential.equals(that.playerCredential)) return false;
        if (!socialConnectionData.equals(that.socialConnectionData)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerCredential.hashCode();
        result = 31 * result + socialConnectionData.hashCode();
        return result;
    }

}
