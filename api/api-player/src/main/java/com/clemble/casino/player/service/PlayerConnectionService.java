package com.clemble.casino.player.service;

import java.util.List;
import java.util.Set;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.PlayerConnections;
import com.clemble.casino.player.PlayerProfile;
import org.springframework.social.connect.ConnectionKey;

public interface PlayerConnectionService extends ClembleService {

    // TODO remove from public access
    public PlayerConnections getConnections(String player);

    // TODO remove from public access
    public Set<ConnectionKey> getOwnedConnections(String player);

    public Set<ConnectionKey> getConnectedConnection(String player);

    public PlayerConnections myConnections();

    public Set<ConnectionKey> myOwnedConnections();

    public Set<ConnectionKey> myConnectedConnections();

}
