package com.clemble.casino.registration.service;

import com.clemble.casino.registration.PlayerSocialGrantRegistrationRequest;
import com.clemble.casino.registration.PlayerSocialRegistrationRequest;

/**
 * Created by mavarazy on 7/4/14.
 */
public interface PlayerSocialRegistrationService extends RegistrationService {

    String register(PlayerSocialRegistrationRequest socialRegistrationRequest);

    String register(PlayerSocialGrantRegistrationRequest grantRegistrationRequest);

}
