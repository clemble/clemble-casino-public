package com.clemble.casino.player.service;

import java.util.Set;

import com.clemble.casino.ClembleService;

public interface PlayerConnectionService extends ClembleService {

    public Set<String> getConnections(String player);

    public Set<String> myConnections();

}
