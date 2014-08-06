package com.clemble.casino.client.player;

import com.clemble.casino.player.PlayerSession;

public interface PlayerSessionOperations {

    public PlayerSession create();

    public PlayerSession refreshPlayerSession(String sessionId);

    public void endPlayerSession(String sessionId);

    public PlayerSession getPlayerSession(String sessionId);

}
