package com.clemble.casino.registration;

import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.validation.MaxSize;
import com.clemble.casino.error.validation.MinSize;
import com.clemble.casino.error.validation.PasswordConstraint;

import javax.validation.constraints.NotNull;

/**
 * Created by mavarazy on 5/4/15.
 */
public interface PasswordAware {

        @PasswordConstraint // TODO unite all requirements for password in @PasswordConstraint
    @MinSize(min = 6, message = ClembleCasinoError.Code.PASSWORD_TOO_SHORT_CODE)
    @MaxSize(max = 64, message = ClembleCasinoError.Code.PASSWORD_TOO_LONG_CODE)
    @NotNull(message = ClembleCasinoError.Code.PASSWORD_MISSING_CODE)

    public String getPassword();
}
