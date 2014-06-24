package com.clemble.casino.player.security;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.clemble.casino.error.ClembleCasinoError.Code;
import com.clemble.casino.error.validation.MaxSize;
import com.clemble.casino.error.validation.MinSize;
import com.clemble.casino.error.validation.PasswordConstraint;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;

public class PlayerCredential implements PlayerAware {

    /**
     * Generated 15/02/13
     */
    private static final long serialVersionUID = 6796999437224779009L;

    @Id
    private String player;

    @Email(message = Code.EMAIL_INVALID_CODE)
    private String email;

    @PasswordConstraint
    @MinSize(min = 6, message = Code.PASSWORD_TOO_SHORT_CODE)
    @MaxSize(max = 64, message = Code.PASSWORD_TOO_LONG_CODE)
    @NotNull(message = Code.PASSWORD_MISSING_CODE)
    private String password;

    public PlayerCredential() {
    }

    @JsonCreator
    public PlayerCredential(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public PlayerCredential setPlayer(String profileId) {
        this.player = profileId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PlayerCredential setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public PlayerCredential setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
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
        PlayerCredential other = (PlayerCredential) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        return true;
    }

}
