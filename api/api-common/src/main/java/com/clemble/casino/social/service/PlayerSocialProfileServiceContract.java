package com.clemble.casino.social.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.social.SocialConnectionData;

public interface PlayerSocialProfileServiceContract extends ClembleService {

    public SocialConnectionData add(String player, SocialConnectionData socialConnectionData);

}
