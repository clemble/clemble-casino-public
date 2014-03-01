package com.clemble.casino.game.event.server;

import com.clemble.casino.game.*;
import com.clemble.casino.game.RoundGamePlayerContext;
import com.clemble.casino.game.outcome.GameOutcome;
import com.clemble.casino.payment.PaymentTransaction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ended")
public class RoundEndedEvent extends RoundEvent implements GameEndedEvent<RoundGamePlayerContext> {

    /**
     * Generated 07/05/13
     */
    private static final long serialVersionUID = 820200145932972096L;

    final private GameOutcome outcome;
    final private GameContext<RoundGamePlayerContext> context;
    private PaymentTransaction transaction;

    public RoundEndedEvent(RoundGameRecord session, GameOutcome outcome) {
        super(session);
        this.outcome = outcome;
        this.context = session.getState().getContext();
    }

    @JsonCreator
    public RoundEndedEvent(@JsonProperty("session") GameSessionKey sessionKey,
                           @JsonProperty("state") GameState state,
                           @JsonProperty("outcome") GameOutcome outcome,
                           @JsonProperty("context") GameContext<RoundGamePlayerContext> context,
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
    public GameContext<RoundGamePlayerContext> getContext() {
        return context;
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
        RoundEndedEvent other = (RoundEndedEvent) obj;
        if (outcome == null) {
            if (other.outcome != null)
                return false;
        } else if (!outcome.equals(other.outcome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "round:ended:" + getSession() + ":" + outcome;
    }
}
