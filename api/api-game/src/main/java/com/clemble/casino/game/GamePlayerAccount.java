package com.clemble.casino.game;

import java.io.Serializable;

import com.clemble.casino.game.specification.GameSpecification;
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

    public GamePlayerAccount(final long left) {
        this.left = left;
        this.owned = 0;
        this.spent = 0;
    }

    @JsonCreator
    public GamePlayerAccount(
            @JsonProperty("left") final long left,
            @JsonProperty("spent") final long spent,
            @JsonProperty("owned") final long owned) {
        this.left = left;
        this.spent = spent;
    }

    public GamePlayerAccount(GameSpecification specification) {
        this(specification.getPrice().getAmount());
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

    @JsonIgnore
    public long fetchMoneyTotal() {
        return spent + left;
    }

    @Override
    public String toString() {
        return "{gpa:" + left + ":" + spent + ":" + owned + "}";
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