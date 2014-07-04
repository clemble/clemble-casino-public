package com.clemble.casino.player.service;

import static com.clemble.casino.web.management.ManagementWebMapping.MANAGEMENT_PLAYER_LOGIN;
import static com.clemble.casino.web.management.ManagementWebMapping.MANAGEMENT_PLAYER_REGISTRATION;
import static com.clemble.casino.web.management.ManagementWebMapping.MANAGEMENT_PLAYER_REGISTRATION_SOCIAL;
import static com.clemble.casino.web.management.ManagementWebMapping.MANAGEMENT_PLAYER_REGISTRATION_SOCIAL_GRANT;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.security.PlayerToken;
import com.clemble.casino.player.web.PlayerLoginRequest;
import com.clemble.casino.player.web.PlayerRegistrationRequest;
import com.clemble.casino.player.web.PlayerSocialGrantRegistrationRequest;
import com.clemble.casino.player.web.PlayerSocialRegistrationRequest;
import com.clemble.casino.web.mapping.WebMapping;

public interface PlayerFacadeRegistrationService
    extends ClembleService,
    PlayerRegistrationService,
    PlayerSocialRegistrationService {

}
