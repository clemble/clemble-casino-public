package com.clemble.casino.bet;

import com.clemble.casino.money.Money;
import com.clemble.casino.payment.AmountAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/16/14.
 */
public class Bid implements AmountAware {

    final private Money amount;
    final private Money interest;

    @JsonCreator
    public Bid(@JsonProperty("amount") Money amount, @JsonProperty("interest") Money interest) {
        this.amount = amount;
        this.interest = interest;
    }

    public Money getAmount() {
        return amount;
    }

    public Money getInterest() {
        return interest;
    }

    public Money total() {
        return amount.add(interest);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (amount != null ? !amount.equals(bid.amount) : bid.amount != null) return false;
        if (interest != null ? !interest.equals(bid.interest) : bid.interest != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (interest != null ? interest.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "bid:" + amount + ":" + interest;
    }
}
