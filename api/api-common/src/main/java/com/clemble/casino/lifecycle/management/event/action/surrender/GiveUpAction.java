package com.clemble.casino.lifecycle.management.event.action.surrender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(GiveUpAction.JSON_TYPE)
public class GiveUpAction extends SurrenderAction {

    /**
     * Generated 29/05/13
     */
    private static final long serialVersionUID = 4501169964446540650L;

    final public static String JSON_TYPE = "player:action:surrender";

    @JsonCreator
    public GiveUpAction() {
    }

    @Override
    public String toString() {
        return JSON_TYPE;
    }

}
