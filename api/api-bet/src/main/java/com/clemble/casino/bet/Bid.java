package com.clemble.casino.bet;

import com.clemble.casino.money.Money;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/10/14.
 */
public class Bid implements PlayerAware {

    final private String player;
    final private String bidder;
    final private Money amount;
    final private Money bidAmount;

    @JsonCreator
    public Bid(@JsonProperty("player") String player, @JsonProperty("bidder") String bidder, @JsonProperty("amount") Money amount, @JsonProperty("bidAmount") Money bidAmount) {
        this.player = player;
        this.amount = amount;
        this.bidder = bidder;
        this.bidAmount = bidAmount;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getBidder() {
        return bidder;
    }

    public Money getAmount() {
        return amount;
    }

    public Money getBidAmount() {
        return bidAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (!amount.equals(bid.amount)) return false;
        if (!bidAmount.equals(bid.bidAmount)) return false;
        if (!bidder.equals(bid.bidder)) return false;
        if (!player.equals(bid.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bidder.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + bidAmount.hashCode();
        return result;
    }
}
