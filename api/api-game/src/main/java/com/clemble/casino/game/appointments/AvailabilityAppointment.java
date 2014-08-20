package com.clemble.casino.game.appointments;

import static com.clemble.casino.utils.Preconditions.checkNotNull;
import com.clemble.casino.game.GameSessionAware;

public class AvailabilityAppointment implements GameSessionAware {

    /**
     * Generated 13/12/13
     */
    private static final long serialVersionUID = -4128392579492490548L;

    final private String sessionKey;

    public AvailabilityAppointment(String sessionKey) {
        this.sessionKey = checkNotNull(sessionKey);
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

}
