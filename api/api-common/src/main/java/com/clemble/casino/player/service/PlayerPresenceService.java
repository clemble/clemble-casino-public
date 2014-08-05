package com.clemble.casino.player.service;

import com.clemble.casino.player.PlayerPresence;

import java.util.List;

/**
 * Created by mavarazy on 8/5/14.
 */
public interface PlayerPresenceService extends PlayerPresenceServiceContract {

    public PlayerPresence myPresence();

    public List<PlayerPresence> getPresences(String ... players);

}
