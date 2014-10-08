package com.clemble.casino.lifecycle.management.event.action.surrender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("moveTimeout")
public class MoveTimeoutSurrenderAction extends SurrenderAction {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = -3052155086475447441L;

    @JsonCreator
    public MoveTimeoutSurrenderAction(@JsonProperty(PLAYER) String player) {
        super(player);
    }

}
