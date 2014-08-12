package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.PlayerConnections;
import org.springframework.social.connect.ConnectionKey;

import java.util.List;
import java.util.Set;

public interface PlayerConnectionServiceContract extends ClembleService {

    // TODO remove from public access
    public PlayerConnections getConnections(String player);

    // TODO remove from public access
    public Set<ConnectionKey> getOwnedConnections(String player);

    public Set<ConnectionKey> getConnectedConnection(String player);

}
