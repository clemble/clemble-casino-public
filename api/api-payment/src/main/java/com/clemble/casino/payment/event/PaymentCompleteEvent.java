package com.clemble.casino.payment.event;

import com.clemble.casino.payment.PaymentOperation;
import com.clemble.casino.money.Money;
import com.clemble.casino.money.Operation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentCompleteEvent.JSON_TYPE)
public class PaymentCompleteEvent implements PaymentEvent {

    final public static String JSON_TYPE = "payment:complete";

    /**
     * Generated 16/12/13
     */
    private static final long serialVersionUID = 2294519340534141788L;

    final private String player;
    final private Money amount;
    final private Operation operation;
    final private String transactionKey;

    public PaymentCompleteEvent(String transactionKey, PaymentOperation paymentOperation) {
        this.player = paymentOperation.getPlayer();
        this.amount = paymentOperation.getAmount();
        this.operation = paymentOperation.getOperation();
        this.transactionKey = transactionKey;
    }

    @JsonCreator
    public PaymentCompleteEvent(@JsonProperty(PLAYER) String player, @JsonProperty("amount") Money amount, @JsonProperty("operation") Operation operation,
                                @JsonProperty(TRANSACTION_KEY) String transactionKey) {
        this.player = player;
        this.amount = amount;
        this.operation = operation;
        this.transactionKey = transactionKey;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Operation getOperation() {
        return operation;
    }

    @Override
    public String getTransactionKey() {
        return transactionKey;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + ((transactionKey == null) ? 0 : transactionKey.hashCode());
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
        PaymentCompleteEvent other = (PaymentCompleteEvent) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (operation != other.operation)
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        if (transactionKey == null) {
            if (other.transactionKey != null)
                return false;
        } else if (!transactionKey.equals(other.transactionKey))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return transactionKey + " > " + JSON_TYPE + " > " + operation + ":" + amount + ":" + player;
    }

}
