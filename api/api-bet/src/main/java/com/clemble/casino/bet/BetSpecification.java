package com.clemble.casino.bet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/13/14.
 */
public class BetSpecification {

    final private long maxBet;
    final private long minBet;
    final private int rate;

    @JsonCreator
    public BetSpecification(@JsonProperty("maxBet") long maxBet, @JsonProperty("minBet") long minBet, @JsonProperty("rate") int rate) {
        this.maxBet = maxBet;
        this.minBet = minBet;
        this.rate = rate;
    }

    public long getMaxBet() {
        return maxBet;
    }

    public long getMinBet() {
        return minBet;
    }

    public int getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetSpecification that = (BetSpecification) o;

        if (maxBet != that.maxBet) return false;
        if (minBet != that.minBet) return false;
        if (rate != that.rate) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (maxBet ^ (maxBet >>> 32));
        result = 31 * result + (int) (minBet ^ (minBet >>> 32));
        result = 31 * result + rate;
        return result;
    }
}
