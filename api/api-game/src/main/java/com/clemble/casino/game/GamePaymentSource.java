package com.clemble.casino.game;

import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.management.outcome.OutcomeAware;
import com.clemble.casino.payment.PaymentSource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/1/14.
 */
@JsonTypeName(GamePaymentSource.JSON_TYPE)
public class GamePaymentSource implements PaymentSource, GameSessionAware, OutcomeAware {

    final public static String JSON_TYPE = "payment:game";

    final private String sessionKey;
    final private Outcome outcome;

    @JsonCreator
    public GamePaymentSource(
        @JsonProperty(SESSION_KEY) String sessionKey,
        @JsonProperty("outcome")Outcome outcome) {
        this.sessionKey = sessionKey;
        this.outcome = outcome;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public Outcome getOutcome() {
        return outcome;
    }

    @Override
    public String toTransactionKey(String player) {
        return sessionKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GamePaymentSource that = (GamePaymentSource) o;

        if (!sessionKey.equals(that.sessionKey)) return false;
        if (!outcome.equals(that.outcome)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 31 * sessionKey.hashCode() + outcome.hashCode();
    }
}
