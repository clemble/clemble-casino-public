package com.clemble.casino.registration.service;

import com.clemble.casino.registration.*;

/**
 * Created by mavarazy on 7/4/14.
 */
public interface PlayerManualRegistrationService extends PlayerRegistrationService {

    public String login(PlayerCredential credentials);

    public String createPlayer(PlayerRegistrationRequest registrationRequest);


}
