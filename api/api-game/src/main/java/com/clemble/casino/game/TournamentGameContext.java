package com.clemble.casino.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("tournamentContext")
public class TournamentGameContext extends GameContext {

    /**
     * Generated 01/04/14
     */
    private static final long serialVersionUID = 8852504586116283067L;

    @JsonCreator
    public TournamentGameContext(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("parent") GameContext<?> parent) {
        super(sessionKey, parent, null);
    }

}
