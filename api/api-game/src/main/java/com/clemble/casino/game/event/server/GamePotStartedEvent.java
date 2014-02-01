package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("potStarted")
public class GamePotStartedEvent extends GamePotEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 3443199915321584272L;

    @JsonCreator
    public GamePotStartedEvent(@JsonProperty("session") GameSessionKey sessionKey) {
        super(sessionKey);
    }

}
