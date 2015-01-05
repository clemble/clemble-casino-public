package com.clemble.casino.payment;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.PlayerBid;
import com.clemble.casino.bet.PlayerBidAware;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by mavarazy on 11/22/14.
 */
public class Bank implements PlayerBidAware {

    // TODO make this immutable
    private Set<PlayerBid> bids = new TreeSet<PlayerBid>(new Comparator<PlayerBid>() {
        @Override
        public int compare(PlayerBid o1, PlayerBid o2) {
            return o1.getPlayer().compareTo(o2.getPlayer());
        }
    });
    private Bid total;

    @JsonCreator
    public Bank(@JsonProperty("bids") Collection<PlayerBid> bids, @JsonProperty("total") Bid total) {
        this.total = total;
        this.bids.addAll(bids);
    }

    @Override
    public Collection<PlayerBid> getBids() {
        return bids;
    }

    public PlayerBid getBid(String player) {
        for(PlayerBid bid: bids) {
            if (bid.getPlayer().equals(player)) {
                return bid;
            }
        }
        return null;
    }

    public Bid getTotal() {
        return total;
    }

    public Bank add(PlayerBid playerBid) {
        PlayerBid existingBid = getBid(playerBid.getPlayer());
        if (existingBid != null) {
            this.bids.remove(existingBid);
            this.bids.add(new PlayerBid(playerBid.getPlayer(), existingBid.getBid().add(playerBid.getBid())));
        } else {
            this.bids.add(playerBid);
        }
        this.total = total.add(playerBid.getBid());
        return this;
    }

    public static Bank create(String player, Bid total) {
        Collection<PlayerBid> bids = Collections.singleton(new PlayerBid(player, total));
        return new Bank(bids, total);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        if (!bids.containsAll(bank.bids) || bids.size() != bank.bids.size()) return false;
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
