package com.clemble.casino.android.player;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.player.service.PlayerNotificationService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static com.clemble.casino.player.PlayerNotificationWebMapping.*;

/**
 * Created by mavarazy on 11/29/14.
 */
public class AndroidPlayerNotificationService extends AbstractClembleCasinoOperations implements PlayerNotificationService {

    final private RestTemplate restTemplate;

    public AndroidPlayerNotificationService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<PlayerNotification> myNotifications() {
        // Step 1. Fetching player notifications
        URI notificationUri = buildUriWith(toNotificationUrl(MY_NOTIFICATIONS));
        // Step 2. Requesting through RestTemplate
        return CollectionUtils.immutableList(restTemplate.getForObject(notificationUri, PlayerNotification[].class));

    }

}
