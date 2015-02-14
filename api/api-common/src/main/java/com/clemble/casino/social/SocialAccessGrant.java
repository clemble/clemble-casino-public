package com.clemble.casino.social;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.oauth2.AccessGrant;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class SocialAccessGrant implements Serializable {

    /**
     * Generated 08/12/13
     */
    private static final long serialVersionUID = -7119702755972096215L;

    private final SocialProvider provider;
    
    private final String accessToken;

    private final String secret;

    private final String scope;

    private final String refreshToken;
    
    private final Long expireTime;

    public SocialAccessGrant(SocialProvider provider, String accessToken) {
        this(provider, accessToken, null, null, null, null);
    }

    @JsonCreator
    public SocialAccessGrant(@JsonProperty("provider") SocialProvider provider,
            @JsonProperty("accessToken") String accessToken,
            @JsonProperty("secret") String secret,
            @JsonProperty("scope") String scope,
            @JsonProperty("refreshToken") String refreshToken,
            @JsonProperty("expireTime") Long expireTime) {
        this.provider = provider;
        this.accessToken = accessToken;
        this.secret = secret;
        this.scope = scope;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
    }

    public SocialProvider getProvider() {
        return provider;
    }

    /**
     * The access token value.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * The Social Access
     */
    public String getSecret() {
        return secret;
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

    /**
     * @return OAuthToken presentation of the token
     */
    public OAuthToken toOAuthToken() {
        return new OAuthToken(accessToken, secret);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
        result = prime * result + ((expireTime == null) ? 0 : expireTime.hashCode());
        result = prime * result + ((provider == null) ? 0 : provider.hashCode());
        result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
        result = prime * result + ((scope == null) ? 0 : scope.hashCode());
        result = prime * result + ((secret == null) ? 0 : secret.hashCode());
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
        SocialAccessGrant other = (SocialAccessGrant) obj;
        if (accessToken == null) {
            if (other.accessToken != null)
                return false;
        } else if (!accessToken.equals(other.accessToken))
            return false;
        if (expireTime == null) {
            if (other.expireTime != null)
                return false;
        } else if (!expireTime.equals(other.expireTime))
            return false;
        if (provider == null) {
            if (other.provider != null)
                return false;
        } else if (!provider.equals(other.provider))
            return false;
        if (refreshToken == null) {
            if (other.refreshToken != null)
                return false;
        } else if (!refreshToken.equals(other.refreshToken))
            return false;
        if (scope == null) {
            if (other.scope != null)
                return false;
        } else if (!scope.equals(other.scope))
            return false;
        if (secret == null) {
            if (other.secret != null)
                return false;
        } else if (!secret.equals(other.secret))
            return false;
        return true;
    }
}
