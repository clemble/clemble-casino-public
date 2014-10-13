package com.clemble.casino.game.lifecycle.construction.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PlayerInvitationDeclinedAction.JSON_TYPE)
public class PlayerInvitationDeclinedAction implements PlayerInvitationAction {

    final public static String JSON_TYPE = "game:construction:invitation:declined:action";

    /**
     * Generated 02/06/13
     */
    private static final long serialVersionUID = 655381424177654890L;

    @JsonCreator
    public PlayerInvitationDeclinedAction() {
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        return getClass() == obj.getClass();
    }

    @Override
    public String toString(){
        return JSON_TYPE;
    }

}
