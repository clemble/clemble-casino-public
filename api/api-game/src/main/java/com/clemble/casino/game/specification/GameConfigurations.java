package com.clemble.casino.game.specification;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameConfigurations {

    final private List<GameConfiguration> configurations;

    @JsonCreator
    public GameConfigurations(@JsonProperty("configurations") List<GameConfiguration> configurations) {
        this.configurations = configurations;
    }

    public List<GameConfiguration> getConfigurations() {
        return configurations;
    }

    public List<MatchGameConfiguration> matchConfigurations() {
        return filter(MatchGameConfiguration.class);
    }

    public List<PotGameConfiguration> potConfigurations(){
        return filter(PotGameConfiguration.class);
    }

    public List<TournamentGameConfiguration> tournamentConfigurations(){
        return filter(TournamentGameConfiguration.class);
    }

    private <T extends GameConfiguration> List<T> filter(Class<T> target) {
        List<T> matchConfigurations = new ArrayList<T>();
        for(GameConfiguration configuration: getConfigurations())
            if(target.isAssignableFrom(configuration.getClass()))
                matchConfigurations.add((T) configuration);
        return matchConfigurations;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((configurations == null) ? 0 : configurations.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameConfigurations other = (GameConfigurations) obj;
        if (configurations == null) {
            if (other.configurations != null)
                return false;
        } else if (!configurations.equals(other.configurations))
            return false;
        return true;
    }

}
