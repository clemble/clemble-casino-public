package com.clemble.casino.game.cell;

import java.util.Collection;

import com.clemble.casino.game.action.BetAction;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("exposed")
public class ExposedCellState extends CellState {

    /**
     * Generated 20/12/13
     */
    private static final long serialVersionUID = -1095977368145429459L;

    final private Collection<BetAction> bets;

    @JsonCreator
    public ExposedCellState(@JsonProperty("owner") String owner, @JsonProperty("bets") Collection<BetAction> bets) {
        super(owner);
        this.bets = CollectionUtils.<BetAction> immutableList(bets);
    }

    public ExposedCellState(Collection<BetAction> bets) {
        this(bets.toArray(new BetAction[0]));
    }

    public ExposedCellState(BetAction... bets) {
        super(BetAction.whoBetMore(bets));
        this.bets = CollectionUtils.<BetAction> immutableList(bets);
    }

    public Collection<BetAction> getBets() {
        return bets;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bets == null) ? 0 : bets.hashCode());
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
        ExposedCellState other = (ExposedCellState) obj;
        if (bets == null) {
            if (other.bets != null)
                return false;
        } else if (!bets.equals(other.bets))
            return false;
        return true;
    }

}