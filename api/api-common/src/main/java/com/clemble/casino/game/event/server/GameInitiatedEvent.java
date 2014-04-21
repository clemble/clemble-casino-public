package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.construct.GameInitiation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("initiated")
public class GameInitiatedEvent extends GameInitiationEvent {

    /**
     * Generated 04/01/14
     */
    private static final long serialVersionUID = 1894571231006850214L;

    public GameInitiatedEvent(GameInitiation initiation) {
        this(initiation.getSession(), initiation);
    }

    @JsonCreator
    public GameInitiatedEvent(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("initiation") GameInitiation initiation) {
        super(sessionKey, initiation);
    }

    @Override
    public String toString() {
        return "initiated:" + super.toString();
    }
    // No need for equals and hashCode - they are inherited from GameManagementEvent
}
