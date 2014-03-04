package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.MatchGameContext;
import com.clemble.casino.game.MatchGamePlayerContext;
import com.clemble.casino.game.outcome.GameOutcome;
import com.clemble.casino.payment.PaymentTransaction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("potEnded")
public class MatchEndedEvent extends MatchEvent implements GameEndedEvent<MatchGamePlayerContext> {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 8084693422808106856L;

    final private GameOutcome outcome;

    @JsonCreator
    public MatchEndedEvent(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("outcome") GameOutcome outcome,
                           @JsonProperty("context") MatchGameContext context) {
        super(sessionKey, context);
        this.outcome = outcome;
    }

    @Override
    public GameOutcome getOutcome() {
        return outcome;
    }

    @Override
    public String toString() {
        return "match:ended:" + getSession() + ":" + outcome;
    }

}
