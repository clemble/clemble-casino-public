package com.clemble.casino.event.surrender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("player:action:surrender")
public class GiveUpAction extends SurrenderEvent {

    /**
     * Generated 29/05/13
     */
    private static final long serialVersionUID = 4501169964446540650L;

    @JsonCreator
    public GiveUpAction(@JsonProperty(PLAYER) String player) {
        super(player);
    }



}
