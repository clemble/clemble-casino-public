package com.clemble.casino.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.clemble.casino.client.ClembleCasinoOperations;
import com.clemble.casino.client.ClembleCasinoRegistrationOperations;
import com.clemble.casino.client.error.ClembleResponseErrorHandler;
import com.clemble.casino.json.ObjectMapperUtils;
import com.clemble.casino.registration.*;
import com.clemble.casino.registration.service.PlayerRegistrationService;
import com.clemble.casino.registration.service.PlayerSocialRegistrationService;
import com.clemble.casino.social.SocialAccessGrant;
import com.clemble.casino.social.SocialConnectionData;
import com.clemble.casino.player.PlayerProfile;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import static com.clemble.casino.registration.RegistrationWebMapping.REGISTRATION_LOGIN;
import static com.clemble.casino.registration.RegistrationWebMapping.REGISTRATION_PROFILE;
import static com.clemble.casino.registration.RegistrationWebMapping.toRegistrationUrl;
import static com.clemble.casino.social.SocialWebMapping.SOCIAL_REGISTRATION_DESCRIPTION;
import static com.clemble.casino.social.SocialWebMapping.SOCIAL_REGISTRATION_GRANT;
import static com.clemble.casino.social.SocialWebMapping.toSocialUrl;
import static com.clemble.casino.utils.Preconditions.checkNotNull;

public class AndroidCasinoRegistrationTemplate implements ClembleCasinoRegistrationOperations {

    final private String host;
    final private AndroidFacadeRegistrationService facadeRegistrationService;

    public AndroidCasinoRegistrationTemplate(String host) {
        this.host = host;
        this.facadeRegistrationService = new AndroidFacadeRegistrationService(host);
    }

    @Override
    public ClembleCasinoOperations login(PlayerCredential playerCredential) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        // PlayerLoginRequest loginRequest = new PlayerLoginRequest(playerCredential);
        // Step 3. Constructing casino operations
        return casinoTemplate(facadeRegistrationService.login(playerCredential));
    }

    @Override
    public ClembleCasinoOperations register(PlayerCredential playerCredential, PlayerProfile playerProfile) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerRegistrationRequest loginRequest = PlayerRegistrationRequest.create(playerCredential, playerProfile);
        // Step 3. Constructing casino operations
        return casinoTemplate(facadeRegistrationService.register(loginRequest));
    }

    @Override
    public ClembleCasinoOperations register(PlayerCredential playerCredential, SocialConnectionData socialConnectionData) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerSocialRegistrationRequest socialRegistrationRequest = new PlayerSocialRegistrationRequest(playerCredential, socialConnectionData);
        // Step 3. Constructing casino operations
        return casinoTemplate(facadeRegistrationService.register(socialRegistrationRequest));
    }

    @Override
    public ClembleCasinoOperations register(PlayerCredential playerCredential, SocialAccessGrant accessGrant) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerSocialGrantRegistrationRequest socialRegistrationRequest = new PlayerSocialGrantRegistrationRequest(playerCredential, accessGrant);
        // Step 3. Generating ClembleTemplate
        return casinoTemplate(facadeRegistrationService.register(socialRegistrationRequest));
    }

    private ClembleCasinoTemplate casinoTemplate(String player) {
        try {
            return new ClembleCasinoTemplate("", "", "", "", player, host);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class AndroidFacadeRegistrationService implements PlayerRegistrationService, PlayerSocialRegistrationService {

        final private String host;
        final private RestTemplate restTemplate;

        public AndroidFacadeRegistrationService(String host) {
            this.host = checkNotNull(host);
            this.restTemplate = new RestTemplate();

            this.restTemplate.setErrorHandler(new ClembleResponseErrorHandler(ObjectMapperUtils.OBJECT_MAPPER));
            MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
            jackson2HttpMessageConverter.setObjectMapper(ObjectMapperUtils.OBJECT_MAPPER);
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(jackson2HttpMessageConverter);

            this.restTemplate.setMessageConverters(messageConverters);
        }

        @Override
        public String login(PlayerCredential loginRequest) {
            return restTemplate.postForObject(toRegistrationUrl(host, REGISTRATION_LOGIN), loginRequest, String.class);
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

}
