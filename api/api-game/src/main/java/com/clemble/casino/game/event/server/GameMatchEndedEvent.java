package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameContext;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.MatchGameRecord;
import com.clemble.casino.game.outcome.GameOutcome;
import com.clemble.casino.payment.PaymentTransaction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ended")
public class GameMatchEndedEvent extends GameMatchEvent implements GameEndedEvent {

    /**
     * Generated 07/05/13
     */
    private static final long serialVersionUID = 820200145932972096L;

    final private GameOutcome outcome;
    final private GameContext context;
    private PaymentTransaction transaction;

    public GameMatchEndedEvent(MatchGameRecord session, GameOutcome outcome) {
        super(session);
        this.outcome = outcome;
        this.context = session.getState().getContext();
    }

    @JsonCreator
    public GameMatchEndedEvent(@JsonProperty("session") GameSessionKey sessionKey,
                               @JsonProperty("state") GameState state,
                               @JsonProperty("outcome") GameOutcome outcome,
                               @JsonProperty("context") GameContext context,
                               @JsonProperty("transaction") PaymentTransaction transaction) {
        super(sessionKey, state);
        this.outcome = outcome;
        this.transaction = transaction;
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

    public PaymentTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(PaymentTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((outcome == null) ? 0 : outcome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameMatchEndedEvent other = (GameMatchEndedEvent) obj;
        if (outcome == null) {
            if (other.outcome != null)
                return false;
        } else if (!outcome.equals(other.outcome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ended:" + getSession() + ":" + getOutcome();
    }
}
