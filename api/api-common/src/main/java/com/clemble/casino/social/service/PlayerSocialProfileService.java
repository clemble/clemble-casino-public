package com.clemble.casino.social.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.social.SocialConnectionData;

public interface PlayerSocialProfileService extends ClembleService {

    public SocialConnectionData add(String player, SocialConnectionData socialConnectionData);

}