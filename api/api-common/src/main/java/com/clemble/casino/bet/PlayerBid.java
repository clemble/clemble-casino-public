package com.clemble.casino.bet;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/10/14.
 */
public class PlayerBid implements PlayerAware, BidAware {

    final private String player;
    final private Bid bid;

    @JsonCreator
    public PlayerBid(
        @JsonProperty("player") String player,
        @JsonProperty("bid") Bid bid) {
        this.player = player;
        this.bid = bid;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public Bid getBid() {
        return bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerBid payerBid = (PlayerBid) o;

        if (!player.equals(payerBid.player)) return false;
        if (!bid.equals(payerBid.bid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bid.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "bid:" + player + ":" + bid;
    }

}
