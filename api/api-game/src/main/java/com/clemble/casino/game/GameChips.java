package com.clemble.casino.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mavarazy on 01/03/14.
 */
public class GameChips {

    final private Map<Integer, Integer> chipToAmount;

    @JsonCreator
    public GameChips(@JsonProperty("chipToAmount") Map<Integer, Integer> chipToAmount) {
        this.chipToAmount = new HashMap<Integer, Integer>(chipToAmount);
    }

    public Map<Integer, Integer> getChipToAmount() {
        return chipToAmount;
    }

    public Integer getAmount(int chip) {
        return chipToAmount.get(chip);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameChips that = (GameChips) o;

        if (chipToAmount != null ? !chipToAmount.equals(that.chipToAmount) : that.chipToAmount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return chipToAmount != null ? chipToAmount.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "gpa:c:" + chipToAmount;
    }

}
