package com.clemble.casino.registration.service;

import com.clemble.casino.registration.PlayerCredential;
import com.clemble.casino.registration.PlayerToken;
import com.clemble.casino.registration.PlayerBaseRegistrationRequest;

/**
 * Created by mavarazy on 7/28/14.
 */
public interface PlayerBaseRegistrationService extends PlayerRegistrationService {

    public String login(PlayerCredential credentials);

    public String register(PlayerBaseRegistrationRequest registrationRequest);

}
