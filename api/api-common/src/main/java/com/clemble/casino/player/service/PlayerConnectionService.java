package com.clemble.casino.player.service;

import org.springframework.social.connect.ConnectionKey;

import java.util.List;

public interface PlayerConnectionService {

    public List<ConnectionKey> getConnections(String player);

}
