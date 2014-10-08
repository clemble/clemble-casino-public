package com.clemble.casino.game.lifecycle.configuration;

import java.util.ArrayList;
import java.util.List;

import com.clemble.casino.game.Game;

public class GameConfigurationUtils {


    private GameConfigurationUtils(List<GameConfiguration> configurations) {
        throw new IllegalAccessError();
    }

    public static GameConfiguration getConfiguration(String configurationKey, List<GameConfiguration> configurations) {
        // Step 1. Sanity check
        if (configurationKey == null)
            return null;
        // Step 2. Searching through out all configurations
        for(GameConfiguration configuration : configurations)
            if(configurationKey.equals(configuration.getConfigurationKey()))
                return configuration;
        // Step 3. Returning default value
        return null;
    }

    public static List<RoundGameConfiguration> matchConfigurations(List<GameConfiguration> configurations) {
        return filter(RoundGameConfiguration.class, configurations);
    }

    public static List<RoundGameConfiguration> matchConfigurations(Game game, List<GameConfiguration> allConfigurations) {
        List<RoundGameConfiguration> roundConfigurations = new ArrayList<RoundGameConfiguration>();
        for (RoundGameConfiguration configuration : filter(RoundGameConfiguration.class, allConfigurations))
            if (game.equals(configuration.getGame()))
                roundConfigurations.add(configuration);
        return roundConfigurations;
    }

    public static List<MatchGameConfiguration> potConfigurations(List<GameConfiguration> configurations) {
        return filter(MatchGameConfiguration.class, configurations);
    }

    public static List<TournamentGameConfiguration> tournamentConfigurations(List<GameConfiguration> configurations) {
        return filter(TournamentGameConfiguration.class, configurations);
    }

    public static <T extends GameConfiguration> List<T> filter(Class<T> target, List<GameConfiguration> configurations) {
        List<T> matchConfigurations = new ArrayList<T>();
        for (GameConfiguration configuration : configurations)
            if (target.isAssignableFrom(configuration.getClass()))
                matchConfigurations.add((T) configuration);
        return matchConfigurations;
    }

}
