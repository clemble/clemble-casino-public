package com.clemble.casino.lifecycle.initiation.service;

import com.clemble.casino.lifecycle.initiation.Initiation;

import java.util.Collection;

/**
 * Created by mavarazy on 9/13/14.
 */
public interface InitiationService<T extends Initiation> {

    T confirm(String key);

    Collection<T> getPending();

    T get(String key);

}
