package com.clemble.casino.payment;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.PlayerBid;
import com.clemble.casino.bet.PlayerBidAware;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mavarazy on 11/22/14.
 */
public class Bank implements PlayerBidAware {

    // TODO make this immutable
    private Collection<PlayerBid> bids;
    private Bid total;

    @JsonCreator
    public Bank(@JsonProperty("bids") Collection<PlayerBid> bids, @JsonProperty("total") Bid total) {
        this.bids = bids;
        this.total = total;
    }

    @Override
    public Collection<PlayerBid> getBids() {
        return bids;
    }

    public Bank add(PlayerBid playerBid) {
        this.bids.add(playerBid);
        this.total = total().add(playerBid.getBid());
        return this;
    }

    public Bid total() {
        return total;
    }

    public static Bank create(String player, Bid total) {
        Collection<PlayerBid> bids = CollectionUtils.immutableList(new PlayerBid(player, total));
        return new Bank(bids, total);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        if (!bids.equals(bank.bids)) return false;
        if (!total.equals(bank.total)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bids.hashCode();
        result = 31 * result + total.hashCode();
        return result;
    }

}
