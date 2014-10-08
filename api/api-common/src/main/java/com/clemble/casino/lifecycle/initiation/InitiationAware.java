package com.clemble.casino.lifecycle.initiation;

/**
 * Created by mavarazy on 9/20/14.
 */
public interface InitiationAware<T extends Initiation> {

    T getInitiation();

}
