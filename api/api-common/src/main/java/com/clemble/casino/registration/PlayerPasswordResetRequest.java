package com.clemble.casino.registration;

import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.validation.MaxSize;
import com.clemble.casino.error.validation.MinSize;
import com.clemble.casino.error.validation.PasswordConstraint;
import com.clemble.casino.player.PlayerAware;

import javax.validation.constraints.NotNull;

/**
 * Created by mavarazy on 2/2/15.
 */
public class PlayerPasswordResetRequest implements PlayerAware {

    final private String player;
    final private String token;
    @PasswordConstraint // TODO unite all requirements for password in @PasswordConstraint
    @MinSize(min = 6, message = ClembleCasinoError.Code.PASSWORD_TOO_SHORT_CODE)
    @MaxSize(max = 64, message = ClembleCasinoError.Code.PASSWORD_TOO_LONG_CODE)
    @NotNull(message = ClembleCasinoError.Code.PASSWORD_MISSING_CODE)
    final private String password;

    public PlayerPasswordResetRequest(String player, String token, String password) {
        this.player = player;
        this.token = token;
        this.password = password;
    }

    @Override
    public String getPlayer() {
        return player;

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
        if (!player.equals(that.player)) return false;
        if (!token.equals(that.token)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + token.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

}
