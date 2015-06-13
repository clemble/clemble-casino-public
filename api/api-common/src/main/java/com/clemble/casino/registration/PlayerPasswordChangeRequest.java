package com.clemble.casino.registration;

import com.clemble.casino.error.validation.ClemblePasswordConstraint;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 5/4/15.
 */
public class PlayerPasswordChangeRequest {

    final private String oldPassword;
    @ClemblePasswordConstraint
    final private String password;

    @JsonCreator
    public PlayerPasswordChangeRequest(
        @JsonProperty("oldPassword") String oldPassword,
        @JsonProperty("password") String password) {
        this.oldPassword = oldPassword;
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerPasswordChangeRequest)) return false;

        PlayerPasswordChangeRequest that = (PlayerPasswordChangeRequest) o;

        if (oldPassword != null ? !oldPassword.equals(that.oldPassword) : that.oldPassword != null) return false;
        return !(password != null ? !password.equals(that.password) : that.password != null);

    }

    @Override
    public int hashCode() {
        int result = oldPassword != null ? oldPassword.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
