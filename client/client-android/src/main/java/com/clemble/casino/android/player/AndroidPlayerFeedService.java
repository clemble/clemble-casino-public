package com.clemble.casino.android.player;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.service.PlayerFeedService;
import com.clemble.casino.post.PlayerPost;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.clemble.casino.player.PlayerPostWebMapping.*;

/**
 * Created by mavarazy on 12/1/14.
 */
public class AndroidPlayerFeedService extends AbstractClembleCasinoOperations implements PlayerFeedService {

    final private RestTemplate restTemplate;

    public AndroidPlayerFeedService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public PlayerPost[] myFeed() {
        // Step 1. Fetching player notifications
        URI feedUri = buildUri(toPostUrl(MY_POSTS));
        // Step 2. Requesting through RestTemplate
        return restTemplate.getForObject(feedUri, PlayerPost[].class);
    }

    @Override
    public PlayerPost[] getFeed(String player) {
        // Step 1. Generating player posts
        URI playerPosts = buildUri(toPostUrl(GET_POSTS), player);
        // Step 2. Requesting through RestTemplate
        return restTemplate.getForObject(playerPosts, PlayerPost[].class);
    }

    @Override
    public PlayerPost share(String key, String provider) {
        // Step 1. Fetching player notifications
        URI shareUri = buildUri(toPostUrl(POST_SHARE), key);
        // Step 2. Requesting through RestTemplate
        return restTemplate.postForObject(shareUri, provider, PlayerPost.class);
    }

}
