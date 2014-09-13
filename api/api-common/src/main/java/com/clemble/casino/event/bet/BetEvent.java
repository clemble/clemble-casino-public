package com.clemble.casino.event.bet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("bet")
public class BetEvent implements PlayerEvent {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 4761116695040560749L;

    final private int bet;
    final private String player;

    @JsonCreator
    public BetEvent(@JsonProperty(PLAYER) String player, @JsonProperty("bet") int bet) {
        this.player = player;
        this.bet = bet;
        if (bet < 0)
            throw new IllegalArgumentException("Bet can't be lesser than 0");

    }

    public int getBet() {
        return bet;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    static public String whoBetMore(BetEvent[] bets) {
        return whoBetMore(Arrays.asList(bets));
    }

    static public String whoBetMore(Collection<BetEvent> bets) {
        if (bets == null || bets.size() == 0)
            return DEFAULT_PLAYER;

        long maxBet = 0;
        String playerWithMaxBet = null;
        for (BetEvent bet : bets) {
            if (bet.getBet() > maxBet) {
                maxBet = bet.getBet();
                playerWithMaxBet = bet.getPlayer();
            } else if(bet.getBet() == maxBet) {
                playerWithMaxBet = DEFAULT_PLAYER;
            }
        }

        return playerWithMaxBet;
    }

    static public Map<String, Collection<BetEvent>> group(Collection<BetEvent> actions) {
        Map<String, Collection<BetEvent>> group = new HashMap<String, Collection<BetEvent>>();
        for(BetEvent bet: actions) {
            if (group.get(bet.getPlayer()) == null)
                group.put(bet.getPlayer(), new ArrayList<BetEvent>());
            group.get(bet.getPlayer()).add(bet);
        }
        return group;
    }
    
    static public Collection<BetEvent> merge(Collection<BetEvent> actions) {
        Map<String, Collection<BetEvent>> grouped = group(actions);
        Collection<BetEvent> resBetAction = new ArrayList<BetEvent>();
        for(String player: grouped.keySet()) {
            int totalBet = 0;
            for(BetEvent bet: grouped.get(player)) {
                totalBet += bet.getBet();
            }
            resBetAction.add(new BetEvent(player, totalBet));
        }
        return resBetAction;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (bet ^ (bet >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BetEvent other = (BetEvent) obj;
        if (bet != other.bet)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "betAction:" + getPlayer() + ":" + bet;
    }

}
