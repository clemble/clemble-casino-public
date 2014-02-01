package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("tournamentEnded")
public class GameTournamentEndedEvent extends GameTournamentEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = -8432784863604445232L;

    @JsonCreator
    public GameTournamentEndedEvent(@JsonProperty("session") GameSessionKey sessionKey) {
        super(sessionKey);
    }

}
