package com.clemble.casino.client.player;

import java.util.List;

import com.clemble.casino.player.PlayerProfile;

public interface PlayerConnectionOperations {

    public List<String> getConnectionIds();

    public List<String> getConnectionIds(String player);

    public List<PlayerProfile> getConnections();

    public List<PlayerProfile> getConnections(String player);

}
