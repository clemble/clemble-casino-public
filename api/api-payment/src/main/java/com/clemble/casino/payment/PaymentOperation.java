package com.clemble.casino.payment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import com.clemble.casino.money.Money;
import com.clemble.casino.money.Operation;
import com.clemble.casino.player.PlayerAware;

@Embeddable
public class PaymentOperation implements PlayerAware, AmountAware {

    /**
     * Generated 05/05/13
     */
    private static final long serialVersionUID = 4480718203883740214L;

    final private String player;
    final private Money amount;
    final private Operation operation;

    @JsonCreator
    public PaymentOperation(@JsonProperty("player") String player, @JsonProperty("amount") Money amount, @JsonProperty("operation") Operation operation) {
        this.player = player;
        this.amount = amount;
        this.operation = operation;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    public Operation getOperation() {
        return operation;
    }

    public PaymentOperation combine(PaymentOperation paymentOperation) {
        // Step 1. Sanity check
        if (paymentOperation == null || !this.player.equals(paymentOperation.getPlayer()))
            throw new IllegalArgumentException();
        // Step 2. Processing operations
        Money newAmount =  paymentOperation.getOperation() == operation ? amount.add(paymentOperation.getAmount()): amount.subtract(paymentOperation.getAmount());
        // Step 3. Checking if value is negative, and returning 
        if(newAmount.isNegative()) {
            return new PaymentOperation(player, newAmount.negate(), operation == Operation.Debit ? Operation.Credit : Operation.Debit);
        } else {
            return new PaymentOperation(player, newAmount, operation);
        }
    }

    @Override
    public String toString() {
        return operation + ":" + player + ":" + amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
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
        PaymentOperation other = (PaymentOperation) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (operation != other.operation)
            return false;
        return player.equals(other.player);
    }

}
