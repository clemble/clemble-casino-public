package com.clemble.casino.game.lifecycle.management.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:tournament:started")
public class TournamentStartedEvent extends TournamentEvent implements GameStartedEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 1692378929431964689L;

    @JsonCreator
    public TournamentStartedEvent(@JsonProperty(SESSION_KEY) String sessionKey) {
        super(sessionKey);
    }

}
