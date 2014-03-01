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
    private PaymentTransaction transaction;

    @JsonCreator
    public MatchEndedEvent(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("outcome") GameOutcome outcome,
                           @JsonProperty("context") MatchGameContext context, @JsonProperty("transaction") PaymentTransaction transaction) {
        super(sessionKey, context);
        this.outcome = outcome;
        this.transaction = transaction;
    }

    @Override
    public GameOutcome getOutcome() {
        return outcome;
    }

    @Override
    public PaymentTransaction getTransaction() {
        return transaction;
    }

    @Override
    public void setTransaction(PaymentTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "match:ended:" + getSession() + ":" + outcome;
    }

}
