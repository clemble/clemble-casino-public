package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.post.PlayerPost;

/**
 * Created by mavarazy on 11/30/14.
 */
public interface PlayerFeedService extends ClembleService {

    PlayerPost[] myFeed();

    PlayerPost[] getFeed(String player);

    PlayerPost share(String key, String provider);

}
