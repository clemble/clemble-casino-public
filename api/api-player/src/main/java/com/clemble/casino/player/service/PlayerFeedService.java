package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.PlayerService;
import com.clemble.casino.post.PlayerPost;
import com.clemble.casino.social.SocialProvider;

/**
 * Created by mavarazy on 11/30/14.
 */
public interface PlayerFeedService extends PlayerService {

    PlayerPost[] myFeed();

    PlayerPost[] getFeed(String player);

    PlayerPost share(String key, SocialProvider provider);

}
