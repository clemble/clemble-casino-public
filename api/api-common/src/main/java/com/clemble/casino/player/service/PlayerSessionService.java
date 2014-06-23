package com.clemble.casino.player.service;

import com.clemble.casino.player.security.PlayerSession;

public interface PlayerSessionService {

    public PlayerSession create(String player);

    public PlayerSession refreshPlayerSession(String player, String sessionId);

    public void endPlayerSession(String player, String sessionId);

    public PlayerSession getPlayerSession(String player, String sessionId);

}
