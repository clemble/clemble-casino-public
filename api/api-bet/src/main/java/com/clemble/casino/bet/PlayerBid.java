package com.clemble.casino.bet;

import com.clemble.casino.money.Money;
import com.clemble.casino.payment.AmountAware;
import com.clemble.casino.player.WinnerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/10/14.
 */
public class PlayerBid implements WinnerAware, BidAware {

    final private String winner;
    final private String bidder;
    final private Bid bid;

    @JsonCreator
    public PlayerBid(@JsonProperty("winner") String winner, @JsonProperty("bidder") String bidder, @JsonProperty("bid") Bid bid) {
        this.winner = winner;
        this.bidder = bidder;
        this.bid = bid;
    }

    @Override
    public String getWinner() {
        return winner;
    }

    public String getBidder() {
        return bidder;
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

        if (!bidder.equals(payerBid.bidder)) return false;
        if (!winner.equals(payerBid.winner)) return false;
        if (!payerBid.bid.equals(payerBid.bid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = winner.hashCode();
        result = 31 * result + bidder.hashCode();
        result = 31 * result + bid.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "bid:winner:" + winner + ":" + bidder + ":" + bid;
    }

}
