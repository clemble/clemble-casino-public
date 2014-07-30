package com.clemble.casino.client.player;

import java.util.List;

import com.clemble.casino.player.PlayerProfile;
import org.springframework.social.connect.ConnectionKey;

public interface PlayerConnectionOperations {

    public List<ConnectionKey> getConnectionIds();

    public List<ConnectionKey> getConnectionIds(String player);

    public List<PlayerProfile> getConnections();

    public List<PlayerProfile> getConnections(String player);

}
