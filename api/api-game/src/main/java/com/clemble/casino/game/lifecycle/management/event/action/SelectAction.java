package com.clemble.casino.game.lifecycle.management.event.action;

import com.clemble.casino.game.lifecycle.management.unit.GameUnit;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(SelectAction.JSON_TYPE)
public class SelectAction<T extends GameUnit> implements PlayerGameAction {

    /**
     * Generated 21/12/13
     */
    private static final long serialVersionUID = -1260530154110392266L;

    final public static String JSON_TYPE = "game:action:select";

    final private T select;

    @JsonCreator
    public SelectAction(@JsonProperty("select") T select) {
        this.select = select;
    }

    public T getSelect() {
        return select;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((select == null) ? 0 : select.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        SelectAction<?> other = (SelectAction<?>) obj;
        if (select == null) {
            if (other.select != null)
                return false;
        } else if (!select.equals(other.select))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return JSON_TYPE + " > " + select;
    }

}
