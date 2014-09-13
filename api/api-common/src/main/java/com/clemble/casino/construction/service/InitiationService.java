package com.clemble.casino.construction.service;

import com.clemble.casino.construction.Initiation;

import java.util.Collection;

/**
 * Created by mavarazy on 9/13/14.
 */
public interface InitiationService<T extends Initiation> {

    Collection<T> getPending();

}
