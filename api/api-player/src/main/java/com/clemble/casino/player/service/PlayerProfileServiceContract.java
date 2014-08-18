package com.clemble.casino.player.service;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.web.mapping.WebMapping;
import com.clemble.casino.web.player.PlayerWebMapping;

public interface PlayerProfileServiceContract extends ClembleService {

    public PlayerProfile getProfile(String player);

    public List<PlayerProfile> getProfiles(Collection<String> players);

}