package com.clemble.casino.game.account;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GamePlayerAccount implements PlayerAware {

    /**
     * Generated
     */
    private static final long serialVersionUID = -1635859321208535243L;

    final private String player;

    private long left;
    private long spent;
    private long owned;

    public GamePlayerAccount(final String player, final long left) {
        this.player = player;
        this.left = left;
        this.owned = 0;
        this.spent = 0;
    }

    @JsonCreator
    public GamePlayerAccount(@JsonProperty(PlayerAware.JSON_ID) final String player,
            @JsonProperty("left") final long left,
            @JsonProperty("spent") final long spent,
            @JsonProperty("owned") final long owned) {
        this.player = player;
        this.left = left;
        this.spent = spent;
    }

    @Override
    public String getPlayer() {
        return player;
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
        return "{gac:" + player + ":" + left + ":" + spent + ":" + owned + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GamePlayerAccount that = (GamePlayerAccount) o;

        if (left != that.left) return false;
        if (owned != that.owned) return false;
        if (spent != that.spent) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (int) (left ^ (left >>> 32));
        result = 31 * result + (int) (spent ^ (spent >>> 32));
        result = 31 * result + (int) (owned ^ (owned >>> 32));
        return result;
    }
}
