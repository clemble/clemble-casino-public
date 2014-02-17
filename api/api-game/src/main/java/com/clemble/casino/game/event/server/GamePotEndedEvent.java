package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.PotGameContext;
import com.clemble.casino.game.PotGamePlayerContext;
import com.clemble.casino.game.outcome.GameOutcome;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("potEnded")
public class GamePotEndedEvent extends GamePotEvent implements GameEndedEvent<PotGamePlayerContext> {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 8084693422808106856L;

    final private GameOutcome outcome;

    @JsonCreator
    public GamePotEndedEvent(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("outcome") GameOutcome outcome, @JsonProperty("context") PotGameContext context) {
        super(sessionKey, context);
        this.outcome = outcome;
    }

    @Override
    public GameOutcome getOutcome() {
        return outcome;
    }

    @Override
    public String toString(){
        return "potEnded:" + getSession();
    }
}
