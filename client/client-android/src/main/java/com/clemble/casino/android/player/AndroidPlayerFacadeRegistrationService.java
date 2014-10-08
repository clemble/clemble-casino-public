package com.clemble.casino.android.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import static com.clemble.casino.player.PlayerWebMapping.*;
import static com.clemble.casino.registration.RegistrationWebMapping.*;

import com.clemble.casino.json.ObjectMapperUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.client.error.ClembleCasinoResponseErrorHandler;
import com.clemble.casino.registration.PlayerToken;
import com.clemble.casino.registration.service.PlayerFacadeRegistrationService;
import com.clemble.casino.registration.PlayerLoginRequest;
import com.clemble.casino.registration.PlayerRegistrationRequest;
import com.clemble.casino.registration.PlayerSocialGrantRegistrationRequest;
import com.clemble.casino.registration.PlayerSocialRegistrationRequest;

public class AndroidPlayerFacadeRegistrationService implements PlayerFacadeRegistrationService {

    final private String host;
    final private RestTemplate restTemplate;

    public AndroidPlayerFacadeRegistrationService(String host) {
        this.host = checkNotNull(host);
        this.restTemplate = new RestTemplate();

        this.restTemplate.setErrorHandler(new ClembleCasinoResponseErrorHandler(ObjectMapperUtils.OBJECT_MAPPER));
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(ObjectMapperUtils.OBJECT_MAPPER);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(jackson2HttpMessageConverter);

        this.restTemplate.setMessageConverters(messageConverters);
    }

    @Override
    public PlayerToken login(PlayerLoginRequest playerLoginRequest) {
        return restTemplate.postForObject(toRegistrationUrl(host, REGISTRATION_LOGIN), playerLoginRequest, PlayerToken.class);
    }

    @Override
    public PlayerToken createPlayer(PlayerRegistrationRequest registrationRequest) {
        return restTemplate.postForObject(toRegistrationUrl(host, REGISTRATION_PROFILE), registrationRequest, PlayerToken.class);
    }

    @Override
    public PlayerToken createSocialPlayer(PlayerSocialRegistrationRequest socialConnectionData) {
        return restTemplate.postForObject(toSocialUrl(host, SOCIAL_REGISTRATION_DESCRIPTION), socialConnectionData, PlayerToken.class);
    }

    @Override
    public PlayerToken createSocialGrantPlayer(PlayerSocialGrantRegistrationRequest socialConnectionData) {
        return restTemplate.postForObject(toSocialUrl(host, SOCIAL_REGISTRATION_GRANT), socialConnectionData, PlayerToken.class);
    }

}
