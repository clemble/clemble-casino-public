package com.clemble.casino.player.service;

import java.util.Collection;

import com.clemble.casino.player.event.PlayerConnection;

public interface PlayerConnectionService {

    public Collection<PlayerConnection> getConnections(String player);

}
