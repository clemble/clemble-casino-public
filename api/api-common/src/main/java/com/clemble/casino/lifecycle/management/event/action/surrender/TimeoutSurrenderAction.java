package com.clemble.casino.lifecycle.management.event.action.surrender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(TimeoutSurrenderAction.JSON_TYPE)
public class TimeoutSurrenderAction extends SurrenderAction {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 6999945454488627240L;

    final public static String JSON_TYPE = "player:action:total:timeout";

    @JsonCreator
    public TimeoutSurrenderAction() {
    }

    @Override
    public String toString() {
        return JSON_TYPE;
    }

}
