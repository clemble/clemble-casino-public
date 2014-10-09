package com.clemble.casino.lifecycle.management;

import java.util.List;

/**
 * Created by mavarazy on 10/9/14.
 */
public interface StateContext<T extends PlayerContext> {

    public StateContext getParent();

    public List<T> getPlayerContexts();

    public T getPlayerContext(String player);

}
