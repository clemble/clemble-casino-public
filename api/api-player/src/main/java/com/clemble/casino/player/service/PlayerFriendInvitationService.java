package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.FriendInvitation;

import java.util.List;

/**
 * Created by mavarazy on 11/11/14.
 */
public interface PlayerFriendInvitationService extends ClembleService {

    public List<FriendInvitation> myRequests();

    public List<FriendInvitation> myOutgoingRequests();

    public List<FriendInvitation> myIncomingRequests();

    public FriendInvitation connect(FriendInvitation friendRequest);

    public FriendInvitation reply(String player, boolean accept);

}
