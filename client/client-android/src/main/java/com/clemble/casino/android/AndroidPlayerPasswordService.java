package com.clemble.casino.android;

import com.clemble.casino.registration.PlayerPasswordChangeRequest;
import com.clemble.casino.registration.PlayerPasswordResetRequest;
import com.clemble.casino.registration.PlayerPasswordRestoreRequest;
import com.clemble.casino.registration.service.PlayerPasswordService;
import org.springframework.web.client.RestTemplate;

import static com.clemble.casino.registration.RegistrationWebMapping.*;
import static com.clemble.casino.utils.Preconditions.checkNotNull;

/**
 * Created by mavarazy on 2/2/15.
 */
public class AndroidPlayerPasswordService implements PlayerPasswordService {

    final private String host;
    final private RestTemplate restTemplate;

    public AndroidPlayerPasswordService(RestTemplate restClientService, String host) {
        this.host = host;
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public boolean restore(PlayerPasswordRestoreRequest restoreRequest) {
        // Step 1. Generating url
        String restoreUrl = toRegistrationUrl(host, RESTORE_PASSWORD);
        // Step 2. Sending appropriate request
        return restTemplate.postForObject(restoreUrl, restoreRequest, Boolean.class);
    }

    @Override
    public boolean reset(PlayerPasswordResetRequest resetRequest) {
        // Step 1. Generating url
        String resetUrl = toRegistrationUrl(host, RESET_PASSWORD);
        // Step 2. Sending appropriate request
        return restTemplate.postForObject(resetUrl, resetRequest, Boolean.class);
    }

    @Override
    public boolean change(PlayerPasswordChangeRequest changeRequest) {
        // Step 1. Generating url
        String changeUrl = toRegistrationUrl(host, CHANGE_PASSWORD);
        // Step 2. Sending appropriate request
        return restTemplate.postForObject(changeUrl, changeRequest, Boolean.class);
    }

}
