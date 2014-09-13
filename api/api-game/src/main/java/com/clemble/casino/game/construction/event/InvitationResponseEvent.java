package com.clemble.casino.game.construction.event;

import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("response")
public interface InvitationResponseEvent extends GameConstructionEvent, PlayerEvent {

}
