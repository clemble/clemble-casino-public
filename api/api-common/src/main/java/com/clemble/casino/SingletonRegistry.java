package com.clemble.casino;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SingletonRegistry implements ServerRegistry {

    /**
     * Generated 
     */
    private static final long serialVersionUID = -8555108439816572727L;

    final private String baseUrl;

    @JsonCreator
    public SingletonRegistry(@JsonProperty("baseUrl") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public String findBase() {
        return baseUrl;
    }

    @Override
    public String findById(String identifier) {
        return baseUrl;
    }

    @Override
    public String findByIdAndType(String identifier, Object type) {
        return baseUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((baseUrl == null) ? 0 : baseUrl.hashCode());
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
        SingletonRegistry other = (SingletonRegistry) obj;
        if (baseUrl == null) {
            if (other.baseUrl != null)
                return false;
        } else if (!baseUrl.equals(other.baseUrl))
            return false;
        return true;
    }

}
