package com.clemble.casino.construct;

import com.clemble.casino.configuration.Configuration;

import java.util.Collection;

/**
 * Created by mavarazy on 9/7/14.
 */
public interface ConstructionService<T extends Configuration, R extends ConstructionRequest<T>> {

    public Construction<T> construct(R request);

    public Collection<? extends Construction<T>> getPending(String player);

}
