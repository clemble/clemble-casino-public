package com.clemble.casino.game.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("default")
public class DefaultGameAction extends GameAction {

    /**
     * Generated 23/12/13
     */
    private static final long serialVersionUID = 6526877866632872028L;

    @JsonCreator
    public DefaultGameAction(@JsonProperty("player") String player) {
        super(player);
    }

}
