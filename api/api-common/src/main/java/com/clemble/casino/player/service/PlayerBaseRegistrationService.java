package com.clemble.casino.player.service;

import com.clemble.casino.player.security.PlayerCredential;
import com.clemble.casino.player.security.PlayerToken;
import com.clemble.casino.player.web.PlayerBaseRegistrationRequest;

/**
 * Created by mavarazy on 7/28/14.
 */
public interface PlayerBaseRegistrationService {

    public PlayerToken login(PlayerCredential credentials);

    public PlayerToken register(PlayerBaseRegistrationRequest registrationRequest);

}
