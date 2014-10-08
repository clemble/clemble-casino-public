package com.clemble.casino.player.service;

import java.util.List;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.PlayerPresence;

public interface PlayerPresenceServiceContract extends ClembleService {

    public PlayerPresence getPresence(String player);

    public List<PlayerPresence> getPresences(List<String> players);

}
