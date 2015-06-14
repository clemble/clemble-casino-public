package com.clemble.casino.player.service;

import com.clemble.casino.PlayerService;

/**
 * Created by mavarazy on 8/5/14.
 */
public interface PlayerImageService extends PlayerService {

    byte[] myImage();

    byte[] getImage(String player);

}
