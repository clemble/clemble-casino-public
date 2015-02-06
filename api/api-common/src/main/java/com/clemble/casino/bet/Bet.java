package com.clemble.casino.bet;

import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.payment.AmountAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/16/14.
 */
public class Bet implements AmountAware {

    final private Money amount;
    final private Money interest;

    @JsonCreator
    public Bet(@JsonProperty("amount") Money amount, @JsonProperty("interest") Money interest) {
        this.amount = amount;
        this.interest = interest;
    }

    public Money getAmount() {
        return amount;
    }

    public Money getInterest() {
        return interest;
    }

    public Bet add(Bet another) {
        return new Bet(amount.add(another.amount), interest.add(another.interest));
    }

    public static Bet create(Currency currency, int amount, int percentage) {
        Money moneyAmount = Money.create(currency, amount);
        Money moneyInterest = moneyAmount.add((amount * percentage) / 100);
        return new Bet(moneyAmount, moneyInterest);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bet bet = (Bet) o;

        if (!amount.equals(bet.amount)) return false;
        if (!interest.equals(bet.interest)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + interest.hashCode();
        return result;
    }

    public String toString() {
        return "bid:" + amount + ":" + interest;
    }

}
