package com.clemble.casino.registration.service;

import com.clemble.casino.registration.PlayerToken;
import com.clemble.casino.registration.PlayerSocialGrantRegistrationRequest;
import com.clemble.casino.registration.PlayerSocialRegistrationRequest;

/**
 * Created by mavarazy on 7/4/14.
 */
public interface PlayerSocialRegistrationService extends PlayerRegistrationService {

    public String createSocialPlayer(PlayerSocialRegistrationRequest socialRegistrationRequest);

    public String createSocialGrantPlayer(PlayerSocialGrantRegistrationRequest grantRegistrationRequest);

}
