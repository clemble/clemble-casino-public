package com.clemble.casino.game.event;

import com.clemble.casino.game.MatchGameContext;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:match:started")
public class MatchStartedEvent extends MatchEvent implements GameStartedEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 3443199915321584272L;

    @JsonCreator
    public MatchStartedEvent(@JsonProperty(SESSION_KEY) String sessionKey, @JsonProperty("context") MatchGameContext context) {
        super(sessionKey, context);
    }

    @Override
    public String toString() {
        return "match:started:" + getSessionKey();
    }

}
