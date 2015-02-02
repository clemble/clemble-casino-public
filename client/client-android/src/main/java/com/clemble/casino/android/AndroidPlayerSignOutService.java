package com.clemble.casino.android;

import static com.clemble.casino.registration.RegistrationWebMapping.*;
import com.clemble.casino.registration.service.PlayerSignOutService;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

/**
 * Created by mavarazy on 1/14/15.
 */
public class AndroidPlayerSignOutService implements PlayerSignOutService {

    final private String host;
    final private RestTemplate restTemplate;

    public AndroidPlayerSignOutService(RestTemplate restClientService, String host) {
        this.host = host;
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public void signOut() {
        // Step 1. Sending signOut request
        restTemplate.getForEntity(toRegistrationUrl(host, REGISTRATION_SIGN_OUT), String.class);
    }

}
