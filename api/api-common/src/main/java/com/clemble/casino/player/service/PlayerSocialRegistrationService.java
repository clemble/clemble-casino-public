package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.security.PlayerToken;
import com.clemble.casino.player.web.PlayerRegistrationRequest;
import com.clemble.casino.player.web.PlayerSocialGrantRegistrationRequest;
import com.clemble.casino.player.web.PlayerSocialRegistrationRequest;

/**
 * Created by mavarazy on 7/4/14.
 */
public interface PlayerSocialRegistrationService extends PlayerRegistrationService {

    public PlayerToken createSocialPlayer(PlayerSocialRegistrationRequest socialRegistrationRequest);

    public PlayerToken createSocialGrantPlayer(PlayerSocialGrantRegistrationRequest grantRegistrationRequest);

}
