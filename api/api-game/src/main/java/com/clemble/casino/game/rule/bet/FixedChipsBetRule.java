package com.clemble.casino.game.rule.bet;

import com.clemble.casino.game.GameChips;
import com.clemble.casino.game.action.BetAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 01/03/14.
 */
@JsonTypeName("fixedChips")
public class FixedChipsBetRule implements BetRule {

    final private GameChips chips;

    @JsonCreator
    public FixedChipsBetRule(@JsonProperty("chips") GameChips chips) {
        this.chips = chips;
    }

    public GameChips getChips() {
        return chips;
    }

    @Override
    public boolean isValid(BetAction betEvent) {
        return chips.getChipToAmount().containsKey(betEvent.getBet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FixedChipsBetRule that = (FixedChipsBetRule) o;

        if (chips != null ? !chips.equals(that.chips) : that.chips != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return chips != null ? chips.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "rule:bet:chips:" + chips;
    }

}
