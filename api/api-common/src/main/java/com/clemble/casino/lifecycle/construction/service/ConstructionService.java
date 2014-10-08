package com.clemble.casino.lifecycle.construction.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.construction.Construction;
import com.clemble.casino.lifecycle.construction.ConstructionRequest;

import java.util.Collection;

/**
 * Created by mavarazy on 9/7/14.
 */
public interface ConstructionService<T extends Configuration, R extends ConstructionRequest<T>> extends ClembleService {

    public Construction<T> construct(R request);

    public Collection<? extends Construction<T>> getPending(String player);

}
