package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.PlayerService;

/**
 * Created by mavarazy on 8/5/14.
 */
public interface PlayerImageService extends PlayerService {

    public byte[] myImage();

    public byte[] mySmallImage();

    public byte[] getImage(String player);

    public byte[] getSmallImage(String player);

}
