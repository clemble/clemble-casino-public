package com.clemble.casino.player.service;

import com.clemble.casino.player.PlayerSession;

public interface PlayerSessionService extends PlayerSessionServiceContract {

    public PlayerSession create();

    public PlayerSession refreshPlayerSession(String sessionId);

    public void endPlayerSession(String sessionId);

    public PlayerSession getPlayerSession(String sessionId);

}
