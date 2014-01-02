package com.clemble.casino.android.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.player.service.PlayerProfileService;
import com.clemble.casino.utils.CollectionUtils;
import static com.clemble.casino.web.player.PlayerWebMapping.*;

public class AndroidPlayerProfileService extends AbstractClembleCasinoOperations implements PlayerProfileService {

    final private RestTemplate restTemplate;

    public AndroidPlayerProfileService(RestTemplate restService, ServerRegistry serverRegistry) {
        super(serverRegistry);
        this.restTemplate = checkNotNull(restService);
    }

    @Override
    public PlayerProfile getPlayerProfile(String player) {
        // Step 1. Generating player Uri
        URI playerUri = buildUriWith(PLAYER_PROFILE, player);
        // Step 2. Sending PlayerProfile request 
        return restTemplate.getForObject(playerUri, PlayerProfile.class);
    }

    @Override
    public PlayerProfile updatePlayerProfile(String player, PlayerProfile playerProfile) {
        // Step 1. Generating player URI
        URI playerUri = buildUriWith(PLAYER_PROFILE, player);
        // Step 2. Post to Player URI
        return restTemplate.postForObject(playerUri, playerProfile, PlayerProfile.class);
    }

    @Override
    public List<PlayerProfile> getPlayerProfile(Collection<String> players) {
        if(players == null)
            return CollectionUtils.immutableList();
        // Step 1. Converting player list to set of parameters
        MultiValueMap<String, String> profiles = new LinkedMultiValueMap<String, String>();
        for(String player: players)
            profiles.add("player", player);
        // Step 1. Generating PlayersURI
        URI playerUri = buildUri(PLAYER_PROFILES, profiles);
        return CollectionUtils.immutableList(restTemplate.getForObject(playerUri, PlayerProfile[].class));
    }

}
