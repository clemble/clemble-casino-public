package com.clemble.casino.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 2/16/15.
 */
public class PlayerLoginRequest {

    final private String emailOrNickName;
    final private String password;

    @JsonCreator
    public PlayerLoginRequest(
        @JsonProperty("emailOrNickName") String emailOrNickName,
        @JsonProperty("password") String password) {
        this.emailOrNickName = emailOrNickName;
        this.password = password;
    }

    public String getEmailOrNickName() {
        return emailOrNickName;
    }

    public String getPassword() {
        return password;
    }

    public static PlayerLoginRequest create(PlayerCredential credential) {
        return new PlayerLoginRequest(credential.getEmail(), credential.getPassword());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerLoginRequest)) return false;

        PlayerLoginRequest that = (PlayerLoginRequest) o;

        if (!emailOrNickName.equals(that.emailOrNickName)) return false;
        if (!password.equals(that.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = emailOrNickName.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
