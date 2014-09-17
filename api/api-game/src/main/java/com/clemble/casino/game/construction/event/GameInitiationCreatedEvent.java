package com.clemble.casino.game.construction.event;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.construction.GameInitiation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:initiation:created")
public class GameInitiationCreatedEvent extends GameInitiationEvent {

    /**
     * Generated 04/01/14
     */
    private static final long serialVersionUID = 1894571231006850214L;

    public GameInitiationCreatedEvent(GameInitiation initiation) {
        this(initiation.getSessionKey(), initiation);
    }

    @JsonCreator
    public GameInitiationCreatedEvent(@JsonProperty(GameSessionAware.SESSION_KEY) String sessionKey, @JsonProperty("initiation") GameInitiation initiation) {
        super(sessionKey, initiation);
    }

    @Override
    public String toString() {
        return "initiated:" + super.toString();
    }
    // No need for equals and hashCode - they are inherited from GameManagementEvent
}
