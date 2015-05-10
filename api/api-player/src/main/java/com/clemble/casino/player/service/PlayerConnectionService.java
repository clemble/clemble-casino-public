package com.clemble.casino.player.service;

import java.util.Set;

import com.clemble.casino.ClembleService;
import com.clemble.casino.PlayerService;

public interface PlayerConnectionService extends PlayerService {

    public Set<String> getConnections(String player);

    public Set<String> myConnections();

}
