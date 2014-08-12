package com.clemble.casino.player.service;

import java.util.List;
import java.util.Set;

import com.clemble.casino.player.PlayerConnections;
import com.clemble.casino.player.PlayerProfile;
import org.springframework.social.connect.ConnectionKey;

public interface PlayerConnectionService extends PlayerConnectionServiceContract {

    public PlayerConnections myConnections();

    public Set<ConnectionKey> myOwnedConnections();

    public Set<ConnectionKey> myConnectedConnections();

}
