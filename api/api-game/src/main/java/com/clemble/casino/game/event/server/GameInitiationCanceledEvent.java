package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.construct.GameInitiation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("initiationCanceled")
public class GameInitiationCanceledEvent extends GameInitiationEvent {

    /**
     * Generated 04/01/14
     */
    private static final long serialVersionUID = -2108886096839930339L;

    @JsonCreator
    public GameInitiationCanceledEvent(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("initiation") GameInitiation initiation) {
        super(sessionKey, initiation);
    }

    public String toString() {
        return "constructionCanceled:" + super.toString();
    }

}
