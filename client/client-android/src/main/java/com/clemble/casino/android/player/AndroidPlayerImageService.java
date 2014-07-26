package com.clemble.casino.android.player;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.service.PlayerImageService;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.clemble.casino.utils.Preconditions.checkNotNull;
import static com.clemble.casino.web.player.PlayerWebMapping.PROFILE_PLAYER_IMAGE;
import static com.clemble.casino.web.player.PlayerWebMapping.toProfileUrl;

/**
 * Created by mavarazy on 7/26/14.
 */
public class AndroidPlayerImageService extends AbstractClembleCasinoOperations implements PlayerImageService {

    final private RestTemplate restTemplate;

    public AndroidPlayerImageService(RestTemplate restService, String host) {
        super(host);
        this.restTemplate = checkNotNull(restService);
    }


    @Override
    public byte[] getImage(String player) {
        // Step 1. Generating player URI
        URI playerUri = buildUriWith(toProfileUrl(PROFILE_PLAYER_IMAGE), player);
        // Step 2. Get for Player Image
        return restTemplate.getForObject(playerUri, byte[].class);

    }
}
