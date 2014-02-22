package com.clemble.casino.payment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import com.clemble.casino.payment.money.Money;
import com.clemble.casino.payment.money.Operation;
import com.clemble.casino.player.PlayerAware;

@Embeddable
public class PaymentOperation implements PlayerAware, AmountAware {

    /**
     * Generated 05/05/13
     */
    private static final long serialVersionUID = 4480718203883740214L;

    @Column(name = "PLAYER_ID")
    private String player;

    @Type(type = "com.clemble.casino.payment.money.MoneyHibernate")
    @Columns(columns = { @Column(name = "CURRENCY"), @Column(name = "AMOUNT") })
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "OPERATION")
    private Operation operation;
    
    public PaymentOperation(){
    }
    
    public PaymentOperation(String player, Money ammount, Operation operation) {
        this.player = player;
        this.amount = ammount;
        this.operation = operation;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public PaymentOperation setPlayer(String playerId) {
        this.player = playerId;
        return this;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    public PaymentOperation setAmount(Money amount) {
        this.amount = amount;
        return this;
    }

    public Operation getOperation() {
        return operation;
    }

    public PaymentOperation setOperation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public void combine(PaymentOperation paymentOperation) {
        // Step 1. Sanity check
        if (paymentOperation == null || !this.player.equals(paymentOperation.getPlayer()))
            throw new IllegalArgumentException();
        // Step 2. Processing operations
        if (paymentOperation.getOperation() == operation) {
            amount = amount.add(paymentOperation.getAmount());
        } else {
            amount = amount.subtract(paymentOperation.getAmount());
        }
        // Step 3. Checking if value is negative, and returning 
        if(amount.isNegative()) {
            operation = operation == Operation.Debit ? Operation.Credit : Operation.Debit;
            amount = amount.negate();
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
