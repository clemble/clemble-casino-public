package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.game.lifecycle.management.MatchGameContext;
import com.clemble.casino.game.lifecycle.management.MatchGameState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:match:started")
public class MatchStartedEvent extends MatchEvent implements GameStartedEvent {

    final public static String JSON_TYPE = "game:management:match:started";

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 3443199915321584272L;

    final private GameInitiation nextInitiation;

    @JsonCreator
    public MatchStartedEvent(
        @JsonProperty(SESSION_KEY) String sessionKey,
        @JsonProperty("state") MatchGameState state,
        @JsonProperty("nextInitiation") GameInitiation nextInitiation) {
        super(sessionKey, state);
        this.nextInitiation = nextInitiation;
    }

    public GameInitiation getNextInitiation() {
        return nextInitiation;
    }

    @Override
    public String toString() {
        return getSessionKey() + " > " + JSON_TYPE;
    }

}
