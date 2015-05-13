package com.clemble.casino.registration.service;

import com.clemble.casino.registration.*;

/**
 * Created by mavarazy on 7/4/14.
 */
public interface PlayerRegistrationService extends RegistrationService {

    public String login(PlayerCredential loginRequest);

    public String register(PlayerRegistrationRequest registrationRequest);


}
