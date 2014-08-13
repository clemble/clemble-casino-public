package com.clemble.casino.player.event;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.player.PlayerPresence;
import com.clemble.casino.player.Presence;
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
            @JsonProperty(GameSessionAware.SESSION_KEY) GameSessionKey session,
            @JsonProperty("presence") Presence presence) {
        super(player, session, presence);
    }

    public PlayerPresenceChangedEvent(PlayerPresence presence) {
        this(presence.getPlayer(), presence.getSessionKey(), presence.getPresence());
    }

}
