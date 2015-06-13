package com.clemble.casino.player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 12/9/14.
 */
public class PlayerPhone {

    final private String phone;

    @JsonCreator
    public PlayerPhone(@JsonProperty("phone") String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerPhone that = (PlayerPhone) o;

        return phone.equals(that.phone);

    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }

}
