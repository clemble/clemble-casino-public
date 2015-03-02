package com.clemble.casino.payment;

import com.clemble.casino.bet.Bet;
import com.clemble.casino.bet.PlayerBet;
import com.clemble.casino.bet.PlayerBetAware;
import com.clemble.casino.money.*;
import com.clemble.casino.money.Currency;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by mavarazy on 11/22/14.
 */
public class Bank implements PlayerBetAware {

    // TODO make this immutable
    private Set<PlayerBet> bets = new TreeSet<PlayerBet>(new Comparator<PlayerBet>() {
        @Override
        public int compare(PlayerBet o1, PlayerBet o2) {
            return o1.getPlayer().compareTo(o2.getPlayer());
        }
    });

    private Bet total;
    private Money penalty;

    @JsonCreator
    public Bank(
        @JsonProperty("bets") Collection<PlayerBet> bets,
        @JsonProperty("total") Bet total,
        @JsonProperty("penalty") Money penalty) {
        this.total = total;
        this.penalty = penalty;
        this.bets.addAll(bets);
    }

    public PlayerBet getBet(String player) {
        for(PlayerBet bet: bets) {
            if (bet.getPlayer().equals(player)) {
                return bet;
            }
        }
        return null;
    }

    @Override
    public Collection<PlayerBet> getBets() {
        return bets;
    }

    public Bet getTotal() {
        return total;
    }

    public Money getPenalty() {
        return penalty;
    }

    public Bank punish(String player, Money amount) {
        Bank bank =  add(new PlayerBet(player, new Bet(Money.create(Currency.point, 0), amount.negate())));
        bank.penalty = bank.penalty.add(amount);
        return bank;
    }

    public Bank add(PlayerBet playerBet) {
        PlayerBet existingBid = getBet(playerBet.getPlayer());
        if (existingBid != null) {
            this.bets.remove(existingBid);
            this.bets.add(new PlayerBet(playerBet.getPlayer(), existingBid.getBet().add(playerBet.getBet())));
        } else {
            this.bets.add(playerBet);
        }
        this.total = total.add(playerBet.getBet());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        if (!bets.containsAll(bank.bets) || bets.size() != bank.bets.size()) return false;
        if (!total.equals(bank.total)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bets.hashCode();
        result = 31 * result + total.hashCode();
        return result;
    }

    public static Bank create(String player, Bet total) {
        Collection<PlayerBet> bets = Collections.singleton(new PlayerBet(player, total));
        return new Bank(bets, total, Money.create(Currency.DEFAULT, 0));
    }

}
