package com.clemble.casino.client.player;

import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.service.PlayerImageService;

/**
 * Created by mavarazy on 7/26/14.
 */
public class PlayerImageTemplate implements PlayerAware, PlayerImageOperations {

    final private String player;
    final private PlayerImageService imageService;

    public PlayerImageTemplate(String player, PlayerImageService imageService) {
        this.player = player;
        this.imageService = imageService;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public byte[] getImage() {
        return imageService.getImage(player);
    }

    @Override
    public byte[] getImage(String player) {
        return imageService.getImage(player);
    }
}
