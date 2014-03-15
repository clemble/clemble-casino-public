package com.clemble.casino.game;

import com.clemble.casino.game.unit.GameUnit;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by mavarazy on 15/03/14.
 */
public class GamePlayerUnit {

    final private List<GameUnit> units;

    @JsonCreator
    public GamePlayerUnit(@JsonProperty("units") List<GameUnit> units) {
        this.units = units;
    }

    public List<GameUnit> getUnits() {
        return units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamePlayerUnit)) return false;

        GamePlayerUnit unit = (GamePlayerUnit) o;

        if (units != null ? !units.equals(unit.units) : unit.units != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return units != null ? units.hashCode() : 0;
    }
}
