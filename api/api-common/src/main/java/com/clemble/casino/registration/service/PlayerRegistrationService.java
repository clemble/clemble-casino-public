package com.clemble.casino.registration.service;

import com.clemble.casino.registration.*;

/**
 * Created by mavarazy on 7/4/14.
 */
public interface PlayerRegistrationService extends RegistrationService {

    String login(PlayerCredential loginRequest);

    String register(PlayerRegistrationRequest registrationRequest);


}
