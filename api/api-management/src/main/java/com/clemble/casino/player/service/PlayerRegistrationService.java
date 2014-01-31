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

public interface PlayerRegistrationService extends ClembleService {

    @RequestMapping(method = RequestMethod.POST, value = MANAGEMENT_PLAYER_LOGIN, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody PlayerToken login(@RequestBody PlayerLoginRequest loginRequest);

    @RequestMapping(method = RequestMethod.POST, value = MANAGEMENT_PLAYER_REGISTRATION, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody PlayerToken createPlayer(@RequestBody final PlayerRegistrationRequest registrationRequest);

    @RequestMapping(method = RequestMethod.POST, value = MANAGEMENT_PLAYER_REGISTRATION_SOCIAL, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody PlayerToken createSocialPlayer(@RequestBody PlayerSocialRegistrationRequest socialRegistrationRequest);

    @RequestMapping(method = RequestMethod.POST, value = MANAGEMENT_PLAYER_REGISTRATION_SOCIAL_GRANT, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody PlayerToken createSocialGrantPlayer(@RequestBody PlayerSocialGrantRegistrationRequest grantRegistrationRequest);

}
