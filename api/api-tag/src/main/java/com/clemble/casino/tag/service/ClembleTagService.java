package com.clemble.casino.tag.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.tag.ClembleTag;

import java.util.List;
import java.util.Set;

/**
 * Created by mavarazy on 2/3/15.
 */
public interface ClembleTagService extends ClembleService {

    public Set<ClembleTag> myTags();

    public Set<ClembleTag> getTags(String player);

}
