package com.clemble.casino.android;

import com.clemble.casino.registration.PlayerPasswordResetRequest;
import com.clemble.casino.registration.service.PlayerPasswordResetService;
import org.springframework.web.client.RestTemplate;

import static com.clemble.casino.registration.RegistrationWebMapping.*;
import static com.clemble.casino.utils.Preconditions.checkNotNull;

/**
 * Created by mavarazy on 2/2/15.
 */
public class AndroidPlayerPasswordResetService implements PlayerPasswordResetService {

    final private String host;
    final private RestTemplate restTemplate;

    public AndroidPlayerPasswordResetService(RestTemplate restClientService, String host) {
        this.host = host;
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public boolean restore(String email) {
        // Step 1. Generating url
        String url = toRegistrationUrl(host, RESTORE_PASSWORD);
        // Step 2. Sending appropriate request
        return restTemplate.postForObject(url, email, Boolean.class);
    }

    @Override
    public boolean reset(PlayerPasswordResetRequest resetRequest) {
        // Step 1. Generating url
        String url = toRegistrationUrl(host, RESET_PASSWORD);
        // Step 2. Sending appropriate request
        return restTemplate.postForObject(url, resetRequest, Boolean.class);

    }
}
