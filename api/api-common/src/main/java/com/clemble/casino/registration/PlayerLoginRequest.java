package com.clemble.casino.registration;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 2/16/15.
 */
public class PlayerLoginRequest implements RegistrationRequest, PlayerAware {

    final private String player;
    final private String emailOrNickName;
    final private String password;

    public PlayerLoginRequest(String emailOrNickName, String password) {
        this.player = null;
        this.emailOrNickName = emailOrNickName;
        this.password = password;
    }

    @JsonCreator
    public PlayerLoginRequest(
        @JsonProperty("player") String player,
        @JsonProperty("emailOrNickName") String emailOrNickName,
        @JsonProperty("password") String password) {
        this.player = player;
        this.emailOrNickName = emailOrNickName;
        this.password = password;
    }

    public String getPlayer() {
        return player;
    }

    public String getEmailOrNickName() {
        return emailOrNickName;
    }

    public String getPassword() {
        return password;
    }

    public static PlayerLoginRequest create(PlayerCredential credential) {
        return new PlayerLoginRequest(null, credential.getEmail(), credential.getPassword());
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
