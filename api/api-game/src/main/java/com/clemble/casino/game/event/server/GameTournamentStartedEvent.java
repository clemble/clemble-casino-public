package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("tournamentStarted")
public class GameTournamentStartedEvent extends GameTournamentEvent implements GameStartedEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 1692378929431964689L;

    @JsonCreator
    public GameTournamentStartedEvent(@JsonProperty("session") GameSessionKey sessionKey) {
        super(sessionKey);
    }

}
