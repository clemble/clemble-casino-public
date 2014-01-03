package com.clemble.casino.game.cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.clemble.casino.game.action.BetAction;
import com.clemble.casino.game.unit.AbstractGameUnit;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("state")
public class CellState extends AbstractGameUnit {

    /**
     * Generated 20/12/13
     */
    private static final long serialVersionUID = 5574663905860524103L;

    final static public CellState DEFAULT = new CellState(PlayerAware.DEFAULT_PLAYER, null);

    final private String owner;
    final private Collection<BetAction> bets;

    public CellState(String owner) {
        this.owner = owner;
        this.bets = CollectionUtils.immutableList();
    }

    @JsonCreator
    public CellState(@JsonProperty("owner") String owner, @JsonProperty(value = "bets", required = false) Collection<BetAction> bets) {
        this.owner = owner;
        this.bets = CollectionUtils.<BetAction> immutableList(bets);
    }

    public CellState(Collection<BetAction> bets) {
        this(BetAction.whoBetMore(bets), bets);
    }

    public Collection<BetAction> getBets() {
        return bets;
    }

    public long getBet(String player) {
        // Step 1. Sanity check
        if (player == null)
            return 0;
        // Step 2. Looking for appropriate player bet
        for (BetAction bet : bets)
            if (player.equals(bet.getPlayer()))
                return bet.getBet();
        // Step 3. No player found
        return 0;
    }

    public String getOwner() {
        return owner;
    }

    public boolean owned() {
        return !owner.equals(PlayerAware.DEFAULT_PLAYER);
    }
    
    public CellState merge(CellState newState) {
        if(bets.isEmpty()) {
            return newState;
        } else {
            Collection<BetAction> allBets = new ArrayList<BetAction>(bets);
            allBets.addAll(newState.getBets());
            return new CellState(BetAction.merge(allBets));
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (owner == null ? 0 : owner.hashCode());
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
        CellState other = (CellState) obj;
        return owner.equals(other.owner);
    }

    @Override
    public String toString() {
        return owner;
    }

    public static CellState[][] emptyBoard(int height, int width) {
        // Step 1. Creating result board
        CellState[][] board = new CellState[height][width];
        // Step 2. Filling empty board
        for (CellState[] row : board)
            Arrays.fill(row, CellState.DEFAULT);
        // Step 3. Returning board
        return board;
    }
}
