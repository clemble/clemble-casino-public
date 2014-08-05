package com.clemble.casino.game;

import java.io.Serializable;

import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GamePlayerAccount implements Serializable {

    /**
     * Generated
     */
    private static final long serialVersionUID = -1635859321208535243L;

    private long left;
    private long spent;
    private long owned;

    public GamePlayerAccount(long left) {
        this.left = left;
        this.owned = 0;
        this.spent = 0;
    }

    @JsonCreator
    public GamePlayerAccount(
            @JsonProperty("left") long left,
            @JsonProperty("spent") long spent,
            @JsonProperty("owned") long owned) {
        this.left = left;
        this.spent = spent;
        this.owned = owned;
    }

    public GamePlayerAccount(Money price) {
        this(price.getAmount());
    }

    public long getLeft() {
        return left;
    }

    public void subLeft(long money) {
        this.left = left - money;
        this.spent = spent + money;
    }

    public long getSpent() {
        return spent;
    }

    public long getOwned() {
        return owned;
    }

    public void addOwned(long amount) {
        this.owned += amount;
    }

    public boolean canAfford(int bet) {
        return bet <= left;
    }

    @JsonIgnore
    public long fetchMoneyTotal() {
        return spent + left;
    }

    @Override
    public String toString() {
        return "gpa:" + left + ":" + spent + ":" + owned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GamePlayerAccount that = (GamePlayerAccount) o;

        if (left != that.left) return false;
        if (owned != that.owned) return false;
        if (spent != that.spent) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (left ^ (left >>> 32));
        result = 31 * result + (int) (spent ^ (spent >>> 32));
        result = 31 * result + (int) (owned ^ (owned >>> 32));
        return result;
    }
}
