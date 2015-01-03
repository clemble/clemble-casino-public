package com.clemble.casino.android.player;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.Invitation;
import com.clemble.casino.player.service.PlayerFriendInvitationService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import static com.clemble.casino.player.PlayerConnectionWebMapping.*;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

/**
 * Created by mavarazy on 11/12/14.
 */
public class AndroidPlayerFriendInvitationService extends AbstractClembleCasinoOperations implements PlayerFriendInvitationService {

    final private RestTemplate restTemplate;

    public AndroidPlayerFriendInvitationService(RestTemplate restClientService, String host) {
        super(host);
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public List<Invitation> myInvitations() {
        // Step 1. Generating url
        URI url = buildUri(toConnectionUrl(MY_INVITATIONS));
        // Step 2. Querying service
        return CollectionUtils.immutableList(restTemplate.getForObject(url, Invitation[].class));
    }

    @Override
    public Invitation invite(Invitation invitation) {
        // Step 1. Generating url
        URI url = buildUri(toConnectionUrl(MY_INVITATIONS));
        // Step 2. Querying service
        return restTemplate.postForObject(url, invitation, Invitation.class);
    }

    @Override
    public Invitation reply(String player, boolean accept) {
        // Step 1. Generating url
        URI url = buildUri(toConnectionUrl(MY_INVITATIONS_REPLY), player);
        // Step 2. Querying service
        return restTemplate.postForObject(url, accept, Invitation.class);
    }

}
