package com.clemble.casino.lifecycle.management.event.action.surrender;

import com.clemble.casino.lifecycle.management.event.action.Action;

abstract public class SurrenderAction implements Action {

    /**
     * Generated 10/06/2013
     */
    private static final long serialVersionUID = 4875966049653785672L;

    public SurrenderAction() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SurrenderAction)) return false;
        return o.getClass() == getClass();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
