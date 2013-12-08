package com.clemble.casino.player;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.social.oauth2.AccessGrant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SocialAccessGrant implements Serializable {

    /**
     * Generated 08/12/13
     */
    private static final long serialVersionUID = -7119702755972096215L;

    private final String provider;
    
    private final String accessToken;

    private final String scope;

    private final String refreshToken;
    
    private final Long expireTime;

    public SocialAccessGrant(String provider, String accessToken) {
        this(provider, accessToken, null, null, null);
    }

    @JsonCreator
    public SocialAccessGrant(@JsonProperty("provider") String provider,
            @JsonProperty("accessToken") String accessToken,
            @JsonProperty("scope") String scope,
            @JsonProperty("refreshToken") String refreshToken,
            @JsonProperty("expireTime") Long expireTime) {
        this.provider = provider;
        this.accessToken = accessToken;
        this.scope = scope;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
    }

    public String getProvider() {
        return provider;
    }

    /**
     * The access token value.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * The scope of the access grant.
     * May be null if the provider doesn't return the granted scope in the response.
     */
    public String getScope() {
        return scope;
    }

    /**
     * The refresh token that can be used to renew the access token.
     * May be null if the provider does not support refresh tokens.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * The time (in milliseconds since Jan 1, 1970 UTC) when this access grant will expire.
     * May be null if the token is non-expiring.
     */
    public Long getExpireTime() {
        return expireTime;
    }

    /**
     * @return AccessGrant presentation of the token
     */
    public AccessGrant toAccessGrant(){
        return new AccessGrant(accessToken, scope, refreshToken, expireTime != null ? TimeUnit.MILLISECONDS.toSeconds(expireTime - System.currentTimeMillis()) : 600);
    }
}
