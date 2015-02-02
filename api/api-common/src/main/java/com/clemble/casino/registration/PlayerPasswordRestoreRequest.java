package com.clemble.casino.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 2/2/15.
 */
public class PlayerPasswordRestoreRequest {

    final private String email;

    @JsonCreator
    public PlayerPasswordRestoreRequest(@JsonProperty("email") String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerPasswordRestoreRequest)) return false;

        PlayerPasswordRestoreRequest that = (PlayerPasswordRestoreRequest) o;

        if (!email.equals(that.email)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

}
