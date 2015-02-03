package com.clemble.casino.android;

import com.clemble.casino.tag.ClembleTag;
import com.clemble.casino.tag.service.PlayerTagService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Set;

import static com.clemble.casino.tag.TagWebMapping.*;
import static com.clemble.casino.utils.Preconditions.checkNotNull;

/**
 * Created by mavarazy on 2/3/15.
 */
public class AndroidPlayerTagService extends AbstractClembleCasinoOperations implements PlayerTagService {

    final private RestTemplate restTemplate;

    public AndroidPlayerTagService(RestTemplate restClientService, String host) {
        super(host);
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public Set<ClembleTag> myTags() {
        // Step 1. Generating my tag url
        URI tagUri = buildUri(toTagUrl(MY_TAGS));
        // Step 2. Requesting through RestTemplate
        return CollectionUtils.immutableSet(restTemplate.getForObject(tagUri, ClembleTag[].class));

    }

    @Override
    public Set<ClembleTag> getTags(String player) {
        // Step 1. Generating player tag url
        URI playerTagUri = buildUri(toTagUrl(GET_TAGS), player);
        // Step 2. Requesting through RestTemplate
        return CollectionUtils.immutableSet(restTemplate.getForObject(playerTagUri, ClembleTag[].class));
    }

}
