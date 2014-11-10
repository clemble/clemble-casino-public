package com.clemble.casino.player.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PlayerInvitationAcceptedAction.JSON_TYPE)
public class PlayerInvitationAcceptedAction implements PlayerInvitationAction {

    final public static String JSON_TYPE = "game:construction:invitation:accepted:action";

    /**
     * Generated 02/06/2013
     */
    private static final long serialVersionUID = -4465974655141746411L;

    @JsonCreator
    public PlayerInvitationAcceptedAction() {
    }

    @Override
    public int hashCode() {
        return 7;
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
