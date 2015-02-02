package com.clemble.casino.android;

import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.player.service.PlayerEmailService;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.clemble.casino.player.PlayerEmailWebMapping.*;
import static com.clemble.casino.player.PlayerEmailWebMapping.toEmailUrl;
import static com.clemble.casino.utils.Preconditions.checkNotNull;

/**
 * Created by mavarazy on 2/2/15.
 */
public class AndroidPlayerEmailService extends AbstractClembleCasinoOperations implements PlayerEmailService {

    final private RestTemplate restTemplate;

    public AndroidPlayerEmailService(RestTemplate restClientService, String host) {
        super(host);
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public String myEmail() {
        // Step 1. Fetching player notifications
        URI emailUri = buildUri(toEmailUrl(MY));
        // Step 2. Requesting through RestTemplate
        return restTemplate.getForObject(emailUri, String.class);

    }

    @Override
    public boolean verify(String verificationCode) {
        // Step 1. Fetching player notifications
        URI verifyUri = buildUri(toEmailUrl(VERIFY), verificationCode);
        // Step 2. Requesting through RestTemplate
        return restTemplate.getForObject(verifyUri, Boolean.class);
    }

}
