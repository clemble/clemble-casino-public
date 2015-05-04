package com.clemble.casino.registration;

import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.validation.ClemblePasswordConstraint;
import com.clemble.casino.error.validation.MaxSize;
import com.clemble.casino.error.validation.MinSize;
import com.clemble.casino.error.validation.PasswordConstraint;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by mavarazy on 2/2/15.
 */
public class PlayerPasswordResetRequest {

    final private String token;
    @ClemblePasswordConstraint
    final private String password;

    @JsonCreator
    public PlayerPasswordResetRequest(
        @JsonProperty("token") String token,
        @JsonProperty("password") String password) {
        this.token = token;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerPasswordResetRequest)) return false;

        PlayerPasswordResetRequest that = (PlayerPasswordResetRequest) o;

        if (!password.equals(that.password)) return false;
        if (!token.equals(that.token)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

}
