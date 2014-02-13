package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameContext;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.outcome.GameOutcome;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("potEnded")
public class GamePotEndedeEvent extends GamePotEvent implements GameEndedEvent {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 8084693422808106856L;

    final private GameOutcome outcome;
    final private GameContext context;

    @JsonCreator
    public GamePotEndedeEvent(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("outcome") GameOutcome outcome, @JsonProperty("context") GameContext context) {
        super(sessionKey);
        this.outcome = outcome;
        this.context = context;
    }

    @Override
    public GameOutcome getOutcome() {
        return outcome;
    }

    @Override
    public GameContext getContext() {
        return context;
    }

}
