package com.clemble.casino.game.construction.event;

import com.clemble.casino.event.PlayerAwareEvent;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("response")
public interface InvitationResponseEvent extends GameConstructionEvent, PlayerAwareEvent {

}
