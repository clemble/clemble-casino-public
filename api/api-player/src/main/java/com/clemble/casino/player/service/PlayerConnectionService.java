package com.clemble.casino.player.service;

import java.util.List;

import com.clemble.casino.player.PlayerProfile;
import org.springframework.social.connect.ConnectionKey;

public interface PlayerConnectionService extends PlayerConnectionServiceContract {

    public List<ConnectionKey> myConnections();

}
