package com.clemble.casino.lifecycle.management.event.action.surrender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(MoveTimeoutSurrenderAction.JSON_TYPE)
public class MoveTimeoutSurrenderAction extends SurrenderAction {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = -3052155086475447441L;

    final public static String JSON_TYPE = "player:action:move:timeout";

    @JsonCreator
    public MoveTimeoutSurrenderAction() {
        super();
    }

    @Override
    public String toString() {
        return JSON_TYPE;
    }

}
