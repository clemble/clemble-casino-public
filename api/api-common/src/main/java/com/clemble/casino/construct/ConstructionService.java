package com.clemble.casino.construct;

import com.clemble.casino.configuration.Configuration;

/**
 * Created by mavarazy on 9/7/14.
 */
public interface ConstructionService<T extends Configuration> {

    public Construction<T> construct(ConstructionRequest<T> configuration);

}
