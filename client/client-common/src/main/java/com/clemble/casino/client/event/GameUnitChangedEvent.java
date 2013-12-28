package com.clemble.casino.client.event;

import com.clemble.casino.game.unit.GameUnit;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameUnitChangedEvent implements GameUnitEvent {

    /**
     * Generated 29/12/13
     */
    private static final long serialVersionUID = -3276072169949020489L;

    final private GameUnit oldUnit;
    final private GameUnit currentUnit;

    @JsonCreator
    public GameUnitChangedEvent(@JsonProperty("oldUnit") GameUnit oldUnit, @JsonProperty("currentUnit") GameUnit currentUnit) {
        this.oldUnit = oldUnit;
        this.currentUnit = currentUnit;
    }

    public GameUnit getOldUnit() {
        return oldUnit;
    }

    public GameUnit getCurrentUnit() {
        return currentUnit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currentUnit == null) ? 0 : currentUnit.hashCode());
        result = prime * result + ((oldUnit == null) ? 0 : oldUnit.hashCode());
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
        GameUnitChangedEvent other = (GameUnitChangedEvent) obj;
        if (currentUnit == null) {
            if (other.currentUnit != null)
                return false;
        } else if (!currentUnit.equals(other.currentUnit))
            return false;
        if (oldUnit == null) {
            if (other.oldUnit != null)
                return false;
        } else if (!oldUnit.equals(other.oldUnit))
            return false;
        return true;
    }

}
