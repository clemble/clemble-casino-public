package com.clemble.casino.registration;

import com.clemble.casino.error.ClembleErrorCode;
import com.clemble.casino.error.validation.*;
import com.clemble.casino.player.EmailAware;
import com.fasterxml.jackson.annotation.JsonCreator;

import com.clemble.casino.error.ClembleErrorCode.Code;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

public class PlayerCredential implements EmailAware {

    /**
     * Generated 15/02/13
     */
    private static final long serialVersionUID = 6796999437224779009L;

    @MinSize(min = 1, message = Code.EMAIL_MISSING)
    @NotNull(message = Code.EMAIL_MISSING)
    @Email(message = Code.EMAIL_INVALID)
    final private String email;

    @ClemblePasswordConstraint
    final private String password;

    @JsonCreator
    public PlayerCredential(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
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
        return true;
    }

}
