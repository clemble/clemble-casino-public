package com.clemble.casino.player.service;

import com.clemble.casino.PlayerService;
import com.clemble.casino.player.PlayerConnectionInvitation;

import java.util.List;

/**
 * Created by mavarazy on 11/11/14.
 */
public interface PlayerConnectionInvitationService extends PlayerService {

    List<PlayerConnectionInvitation> myInvitations();

    PlayerConnectionInvitation invite(String player);

    PlayerConnectionInvitation reply(String player, boolean accept);

}
