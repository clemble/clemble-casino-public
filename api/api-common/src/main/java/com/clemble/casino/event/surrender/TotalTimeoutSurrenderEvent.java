package com.clemble.casino.event.surrender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("totalTimeBreached")
public class TotalTimeoutSurrenderEvent extends SurrenderEvent {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 6999945454488627240L;

    @JsonCreator
    public TotalTimeoutSurrenderEvent(@JsonProperty(PLAYER) String playerId) {
        super(playerId);
    }

}
