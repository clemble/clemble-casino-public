package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.PlayerService;
import com.clemble.casino.player.Invitation;

import java.util.List;

/**
 * Created by mavarazy on 11/11/14.
 */
public interface PlayerFriendInvitationService extends PlayerService {

    public List<Invitation> myInvitations();

    public Invitation invite(Invitation player);

    public Invitation reply(String player, boolean accept);

}
