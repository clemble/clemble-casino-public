package com.clemble.casino.player.service;

import java.util.Set;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.FriendInvitation;
import com.clemble.casino.player.PlayerConnections;
import com.clemble.casino.player.event.PlayerInvitationAction;
import org.springframework.social.connect.ConnectionKey;

public interface PlayerConnectionService extends ClembleService {

    public Set<String> getConnections(String player);

    public Set<String> myConnections();

}
