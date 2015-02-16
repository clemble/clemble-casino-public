package com.clemble.casino.android.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import static com.clemble.casino.social.SocialWebMapping.*;
import static com.clemble.casino.registration.RegistrationWebMapping.*;

import com.clemble.casino.json.ObjectMapperUtils;
import com.clemble.casino.registration.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.client.error.ClembleCasinoResponseErrorHandler;
import com.clemble.casino.registration.service.FacadeRegistrationService;

public class AndroidFacadeRegistrationService implements FacadeRegistrationService {

    final private String host;
    final private RestTemplate restTemplate;

    public AndroidFacadeRegistrationService(String host) {
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
    public String login(PlayerCredential playerCredential) {
        return restTemplate.postForObject(toRegistrationUrl(host, REGISTRATION_LOGIN), playerCredential, String.class);
    }

    @Override
    public String register(PlayerRegistrationRequest registrationRequest) {
        return restTemplate.postForObject(toRegistrationUrl(host, REGISTRATION_PROFILE), registrationRequest, String.class);
    }

    @Override
    public String register(PlayerSocialRegistrationRequest socialConnectionData) {
        return restTemplate.postForObject(toSocialUrl(host, SOCIAL_REGISTRATION_DESCRIPTION), socialConnectionData, String.class);
    }

    @Override
    public String register(PlayerSocialGrantRegistrationRequest socialConnectionData) {
        return restTemplate.postForObject(toSocialUrl(host, SOCIAL_REGISTRATION_GRANT), socialConnectionData, String.class);
    }

}
