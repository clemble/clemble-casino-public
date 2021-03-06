package com.clemble.casino.money;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "positive", "negative" })
public class Money implements Serializable {

    /**
     * Generated 07/04/13
     */
    private static final long serialVersionUID = -2196796622087364501L;

    final public static Money ZERO = Money.create(Currency.DEFAULT, 0);
    final public static Money INSPIRATION = Money.create(Currency.inspiration, 1);

    final private Currency currency;

    final private long amount;

    @JsonCreator
    public Money(@JsonProperty("currency") final Currency currency, @JsonProperty("amount") final long amount) {
        if (currency == null)
            throw new IllegalArgumentException();
        this.currency = currency;
        this.amount = amount;
    }

    public boolean isPositive() {
        return amount > 0;
    }

    public boolean isNegative() {
        return amount < 0;
    }

    public long getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money add(Money more) {
        if (more == null || more.amount == 0)
            return this;

        more = more.exchange(more, currency);

        return Money.create(currency, amount + more.amount);
    }

    public Money process(Operation operation, Money amount) {
        switch (operation) {
            case Credit:
                return subtract(amount);
            case Debit:
                return add(amount);
            default:
                throw new IllegalArgumentException();
        }
    }

    public Money add(long additionalAmount) {
        return Money.create(currency, this.amount + additionalAmount);
    }

    public Money subtract(Money minus) {
        if (minus == null || minus.amount == 0)
            return this;

        minus = exchange(minus, currency);

        return Money.create(currency, amount - minus.amount);
    }

    public Money negate() {
        if (amount == 0)
            return this;

        return Money.create(currency, -amount);
    }

    public Money exchange(Money source, Currency targetCurrency) {
        if (source == null || source.getCurrency() == targetCurrency)
            return source;

        throw new IllegalArgumentException("We do not support money change yet");
    }

    public static Money create(final Currency currency, final long amount) {
        return new Money(currency, amount);
    }

    @Override
    public String toString() {
        return currency + ":" + amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (amount ^ (amount >>> 32));
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
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
        Money other = (Money) obj;
        if (amount != other.amount)
            return false;
        return currency == other.currency;
    }

}
