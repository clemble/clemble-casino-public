package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import org.springframework.social.connect.ConnectionKey;

import java.util.List;

public interface PlayerConnectionServiceContract extends ClembleService {

    public List<ConnectionKey> getConnections(String player);

}
