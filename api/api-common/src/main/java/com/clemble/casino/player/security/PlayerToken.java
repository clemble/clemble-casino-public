package com.clemble.casino.player.security;

import javax.crypto.SecretKey;

import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.client.RSAKeySecretFormat.KeySerializer;
import com.clemble.casino.player.client.RSAKeySecretFormat.SecretKeyDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PlayerToken implements PlayerAware {

    /**
     * Generated 15/02/13
     */
    private static final long serialVersionUID = 7757779068859351877L;

    final private String player;
    final private String consumerKey;
    final private SecretKey secret;
    final private String value;

    @JsonCreator
    public PlayerToken(@JsonProperty(PlayerAware.JSON_ID) String player,
            @JsonSerialize(using = KeySerializer.class)
            @JsonDeserialize(using = SecretKeyDeserializer.class)
            @JsonProperty("secretKey") SecretKey secret,
            @JsonProperty("consumerKey") String consumerKey,
            @JsonProperty("value") String value) {
        this.player = player;
        this.secret = secret;
        this.consumerKey = consumerKey;
        this.value = value;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public SecretKey getSecretKey() {
        return secret;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((consumerKey == null) ? 0 : consumerKey.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + ((secret == null) ? 0 : secret.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        PlayerToken other = (PlayerToken) obj;
        if (consumerKey == null) {
            if (other.consumerKey != null)
                return false;
        } else if (!consumerKey.equals(other.consumerKey))
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        if (secret == null) {
            if (other.secret != null)
                return false;
        } else if (!secret.equals(other.secret))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

}
