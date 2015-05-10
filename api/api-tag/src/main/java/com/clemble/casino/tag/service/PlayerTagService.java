package com.clemble.casino.tag.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.PlayerService;
import com.clemble.casino.tag.ClembleTag;

import java.util.List;
import java.util.Set;

/**
 * Created by mavarazy on 2/3/15.
 */
public interface PlayerTagService extends PlayerService {

    public Set<ClembleTag> myTags();

    public Set<ClembleTag> getTags(String player);

}
