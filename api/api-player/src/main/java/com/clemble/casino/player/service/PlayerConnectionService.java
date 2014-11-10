package com.clemble.casino.player.service;

import java.util.List;
import java.util.Set;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.ConnectionRequest;
import com.clemble.casino.player.PlayerConnections;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.player.event.PlayerInvitationAction;
import org.springframework.social.connect.ConnectionKey;

public interface PlayerConnectionService extends ClembleService {

    // TODO remove from public access
    public PlayerConnections getConnections(String player);

    // TODO remove from public access
    public Set<ConnectionKey> getOwnedConnections(String player);

    public Set<String> getConnectedConnection(String player);

    public ConnectionRequest connect(String player);

    public ConnectionRequest reply(String player, PlayerInvitationAction response);

    public PlayerConnections myConnections();

    public Set<ConnectionKey> myOwnedConnections();

    public Set<String> myConnectedConnections();

}
