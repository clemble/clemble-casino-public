package com.clemble.casino.registration.service;

import com.clemble.casino.registration.PlayerSocialGrantRegistrationRequest;
import com.clemble.casino.registration.PlayerSocialRegistrationRequest;

/**
 * Created by mavarazy on 7/4/14.
 */
public interface PlayerSocialRegistrationService extends RegistrationService {

    public String register(PlayerSocialRegistrationRequest socialRegistrationRequest);

    public String register(PlayerSocialGrantRegistrationRequest grantRegistrationRequest);

}
