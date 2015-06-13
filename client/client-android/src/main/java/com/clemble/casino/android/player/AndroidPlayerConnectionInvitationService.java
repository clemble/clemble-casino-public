package com.clemble.casino.android.player;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.PlayerConnectionInvitation;
import com.clemble.casino.player.service.PlayerConnectionInvitationService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import static com.clemble.casino.player.PlayerConnectionWebMapping.*;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

/**
 * Created by mavarazy on 11/12/14.
 */
public class AndroidPlayerConnectionInvitationService extends AbstractClembleCasinoOperations implements PlayerConnectionInvitationService {

    final private RestTemplate restTemplate;

    public AndroidPlayerConnectionInvitationService(RestTemplate restClientService, String host) {
        super(host);
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public List<PlayerConnectionInvitation> myInvitations() {
        // Step 1. Generating url
        URI url = buildUri(toConnectionUrl(MY_INVITATIONS));
        // Step 2. Querying service
        return CollectionUtils.immutableList(restTemplate.getForObject(url, PlayerConnectionInvitation[].class));
    }

    @Override
    public PlayerConnectionInvitation invite(String invitation) {
        // Step 1. Generating url
        URI url = buildUri(toConnectionUrl(MY_INVITATIONS));
        // Step 2. Querying service
        return restTemplate.postForObject(url, invitation, PlayerConnectionInvitation.class);
    }

    @Override
    public PlayerConnectionInvitation reply(String player, boolean accept) {
        // Step 1. Generating url
        URI url = buildUri(toConnectionUrl(MY_INVITATIONS_REPLY), player);
        // Step 2. Querying service
        return restTemplate.postForObject(url, accept, PlayerConnectionInvitation.class);
    }

}
