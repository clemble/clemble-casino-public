package com.clemble.casino.management;

import com.clemble.casino.KeyAware;
import com.clemble.casino.configuration.Configuration;
import com.clemble.casino.configuration.ConfigurationAware;

import java.util.SortedSet;

/**
 * Created by mavarazy on 9/20/14.
 */
public interface Record<T extends Configuration> extends ConfigurationAware<T>, KeyAware {

    SortedSet<EventRecord> getEventRecords();

}
