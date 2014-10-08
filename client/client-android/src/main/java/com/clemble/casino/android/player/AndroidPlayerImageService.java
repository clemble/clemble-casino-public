package com.clemble.casino.android.player;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.service.PlayerImageService;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.clemble.casino.utils.Preconditions.checkNotNull;
import static com.clemble.casino.player.PlayerWebMapping.*;

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
    public byte[] myImage() {
        // Step 1. Generating player URI
        URI playerUri = buildUriWith(toProfileUrl(MY_IMAGE));
        // Step 2. Get for Player Image
        return restTemplate.getForObject(playerUri, byte[].class);
    }

    @Override
    public byte[] mySmallImage() {
        // Step 1. Generating player URI
        URI playerUri = buildUriWith(toProfileUrl(MY_IMAGE_SMALL));
        // Step 2. Get for Player Image
        return restTemplate.getForObject(playerUri, byte[].class);
    }

    @Override
    public byte[] getImage(String player) {
        // Step 1. Generating player URI
        URI playerUri = buildUriWith(toProfileUrl(PLAYER_IMAGE), player);
        // Step 2. Get for Player Image
        return restTemplate.getForObject(playerUri, byte[].class);
    }

    @Override
    public byte[] getSmallImage(String player) {
        // Step 1. Generating player URI
        URI playerUri = buildUriWith(toProfileUrl(PLAYER_IMAGE_SMALL), player);
        // Step 2. Get for Player Image
        return restTemplate.getForObject(playerUri, byte[].class);
    }

}
