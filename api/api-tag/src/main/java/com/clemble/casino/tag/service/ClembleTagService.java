package com.clemble.casino.tag.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.tag.ClembleTag;

import java.util.List;

/**
 * Created by mavarazy on 2/3/15.
 */
public interface ClembleTagService extends ClembleService {

    public List<ClembleTag> myTags();

    public List<ClembleTag> getTags(String player);

}
