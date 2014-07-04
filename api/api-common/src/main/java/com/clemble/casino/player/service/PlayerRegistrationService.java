package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.security.PlayerToken;
import com.clemble.casino.player.web.PlayerLoginRequest;
import com.clemble.casino.player.web.PlayerRegistrationRequest;

/**
 * Created by mavarazy on 7/4/14.
 */
public interface PlayerRegistrationService extends ClembleService {

    public PlayerToken login(PlayerLoginRequest loginRequest);

    public PlayerToken createPlayer(final PlayerRegistrationRequest registrationRequest);

}
