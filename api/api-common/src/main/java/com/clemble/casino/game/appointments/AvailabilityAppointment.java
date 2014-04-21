package com.clemble.casino.game.appointments;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionKey;

public class AvailabilityAppointment implements GameSessionAware {

    /**
     * Generated 13/12/13
     */
    private static final long serialVersionUID = -4128392579492490548L;

    final private GameSessionKey sessionKey;

    public AvailabilityAppointment(GameSessionKey sessionKey) {
        this.sessionKey = checkNotNull(sessionKey);
    }

    @Override
    public GameSessionKey getSession() {
        return sessionKey;
    }

}
