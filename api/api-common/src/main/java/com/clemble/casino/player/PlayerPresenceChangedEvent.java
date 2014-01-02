package com.clemble.casino.player;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("presence")
public class PlayerPresenceChangedEvent extends PlayerPresence implements PlayerEvent {

    /**
     * Generated 02/01/14
     */
    private static final long serialVersionUID = 1310574608767133952L;

    @JsonCreator
    public PlayerPresenceChangedEvent(@JsonProperty("player") String player, 
            @JsonProperty("session") GameSessionKey session,
            @JsonProperty("presence") Presence presence) {
        super(player, session, presence);
    }

    public PlayerPresenceChangedEvent(PlayerPresence presence) {
        this(presence.getPlayer(), presence.getSession(), presence.getPresence());
    }

}
