package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("potEnded")
public class GamePotEndedeEvent extends GamePotEvent implements GameEndedEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 8084693422808106856L;

    @JsonCreator
    public GamePotEndedeEvent(@JsonProperty("session") GameSessionKey sessionKey) {
        super(sessionKey);
    }

}
