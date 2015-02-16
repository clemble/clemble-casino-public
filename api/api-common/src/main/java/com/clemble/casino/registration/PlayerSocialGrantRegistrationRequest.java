package com.clemble.casino.registration;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.social.SocialAccessGrant;
import com.clemble.casino.social.SocialAccessGrantAware;
import com.clemble.casino.security.ClembleConsumerDetails;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

public class PlayerSocialGrantRegistrationRequest
    implements RegistrationRequest, PlayerCredentialAware, SocialAccessGrantAware {

    private static final long serialVersionUID = -8802470944397014969L;

    final private PlayerCredential playerCredential;
    final private SocialAccessGrant accessGrant;

    @JsonCreator
    public PlayerSocialGrantRegistrationRequest(
            @JsonProperty("playerCredential") final PlayerCredential playerCredential,
            @JsonProperty("accessGrant") final SocialAccessGrant accessGrant) {
        this.playerCredential = checkNotNull(playerCredential);
        this.accessGrant = checkNotNull(accessGrant);
    }

    @Override
    @Valid
    public PlayerCredential getPlayerCredential() {
        return playerCredential;
    }

    @Override
    @Valid
    public SocialAccessGrant getAccessGrant() {
        return accessGrant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerSocialGrantRegistrationRequest)) return false;

        PlayerSocialGrantRegistrationRequest that = (PlayerSocialGrantRegistrationRequest) o;

        if (!accessGrant.equals(that.accessGrant)) return false;
        if (!playerCredential.equals(that.playerCredential)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerCredential.hashCode();
        result = 31 * result + accessGrant.hashCode();
        return result;
    }

}
