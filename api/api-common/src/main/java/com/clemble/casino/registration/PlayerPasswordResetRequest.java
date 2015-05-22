package com.clemble.casino.registration;

import com.clemble.casino.error.ClembleErrorCode;
import com.clemble.casino.error.validation.ClemblePasswordConstraint;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by mavarazy on 2/2/15.
 */
public class PlayerPasswordResetRequest {

    @NotNull(message = ClembleErrorCode.Code.TOKEN_REQUIRED)
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

        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
