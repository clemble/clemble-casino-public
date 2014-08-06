package com.clemble.casino.registration.service;

import com.clemble.casino.registration.PlayerToken;
import com.clemble.casino.registration.PlayerLoginRequest;
import com.clemble.casino.registration.PlayerRegistrationRequest;

/**
 * Created by mavarazy on 7/4/14.
 */
public interface PlayerManualRegistrationService extends PlayerRegistrationService {

    public PlayerToken login(PlayerLoginRequest loginRequest);

    public PlayerToken createPlayer(final PlayerRegistrationRequest registrationRequest);

}
