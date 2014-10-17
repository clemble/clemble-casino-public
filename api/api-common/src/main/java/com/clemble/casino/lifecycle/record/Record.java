package com.clemble.casino.lifecycle.record;

import com.clemble.casino.KeyAware;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.ConfigurationAware;
import com.clemble.casino.lifecycle.management.outcome.OutcomeAware;

import java.util.SortedSet;

/**
 * Created by mavarazy on 9/20/14.
 */
public interface Record<T extends Configuration> extends ConfigurationAware<T>, KeyAware, OutcomeAware {

    SortedSet<EventRecord> getEventRecords();

    RecordState getState();

}
