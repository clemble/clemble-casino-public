package com.clemble.casino.player.service;

import java.util.Set;

import com.clemble.casino.PlayerService;
import com.clemble.casino.player.PlayerConnection;

public interface PlayerConnectionService extends PlayerService {

    Set<PlayerConnection> myConnections();

    Integer myConnectionsCount();

    Set<PlayerConnection> getConnections(String player);

    Integer getConnectionsCount(String player);


}
