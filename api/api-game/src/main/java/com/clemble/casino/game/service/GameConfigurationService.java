package com.clemble.casino.game.service;

import java.util.List;

import com.clemble.casino.game.specification.MatchGameConfiguration;
import com.clemble.casino.game.specification.PotGameConfiguration;
import com.clemble.casino.game.specification.TournamentGameConfiguration;

public interface GameConfigurationService {

    public List<MatchGameConfiguration> getMatchConfigurations();

    public List<PotGameConfiguration> getPotConfigurations();

    public List<TournamentGameConfiguration> getTournamentConfigurations();

}
