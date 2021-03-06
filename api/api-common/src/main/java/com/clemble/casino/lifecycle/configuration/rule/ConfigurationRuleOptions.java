package com.clemble.casino.lifecycle.configuration.rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigurationRuleOptions<T extends ConfigurationRule> {

    final private T defaultOption;

    final private Set<T> allOptions;

    public ConfigurationRuleOptions(T defaultOption, Collection<T> otherOptions) {
        this.defaultOption = defaultOption;
        if (otherOptions == null || otherOptions.size() == 0) {
            this.allOptions = CollectionUtils.immutableSet(defaultOption);
        } else {
            otherOptions = new ArrayList<T>(otherOptions);
            otherOptions.add(defaultOption);
            this.allOptions = CollectionUtils.immutableSet(otherOptions);
        }
    }

    // TODO enable serialization in future
    public ConfigurationRuleOptions(T defaultOption, T... otherOptions) {
        this(defaultOption, Arrays.asList(otherOptions));
    }

    public T getDefault() {
        return defaultOption;
    }

    public Collection<T> getOptions() {
        return allOptions;
    }

    public boolean contains(T option) {
        return allOptions.contains(option);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allOptions == null) ? 0 : allOptions.hashCode());
        result = prime * result + ((defaultOption == null) ? 0 : defaultOption.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConfigurationRuleOptions<T> other = (ConfigurationRuleOptions<T>) obj;
        if (allOptions == null) {
            if (other.allOptions != null)
                return false;
        } else if (!allOptions.equals(other.allOptions))
            return false;
        if (defaultOption == null) {
            if (other.defaultOption != null)
                return false;
        } else if (!defaultOption.equals(other.defaultOption))
            return false;
        return true;
    }

}
