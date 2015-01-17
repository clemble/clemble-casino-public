package com.clemble.casino.bet;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/10/14.
 */
public class PlayerBet implements PlayerAware, BetAware {

    final private String player;
    final private Bet bet;

    @JsonCreator
    public PlayerBet(
        @JsonProperty("player") String player,
        @JsonProperty("bid") Bet bet) {
        this.player = player;
        this.bet = bet;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public Bet getBet() {
        return bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerBet payerBid = (PlayerBet) o;

        if (!player.equals(payerBid.player)) return false;
        if (!bet.equals(payerBid.bet)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bet.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "bid:" + player + ":" + bet;
    }

}
