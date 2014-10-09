package com.clemble.casino.player.event;

import com.clemble.casino.player.PlayerPresence;
import com.clemble.casino.player.Presence;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PlayerPresenceChangedEvent.JSON_TYPE)
public class PlayerPresenceChangedEvent extends PlayerPresence implements PlayerEvent {

    /**
     * Generated 02/01/14
     */
    private static final long serialVersionUID = 1310574608767133952L;

    final public static String JSON_TYPE = "player:presence:changed";

    @JsonCreator
    public PlayerPresenceChangedEvent(@JsonProperty(PLAYER) String player,
            @JsonProperty(SESSION_KEY) String session,
            @JsonProperty("presence") Presence presence) {
        super(player, session, presence);
    }

    public PlayerPresenceChangedEvent(PlayerPresence presence) {
        this(presence.getPlayer(), presence.getSessionKey(), presence.getPresence());
    }

    @Override
    public String toString() {
        return getPlayer() + " > " + JSON_TYPE + " > " + getSessionKey() + " > " + getPresence();
    }

}
