package com.clemble.casino.management.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.configuration.Configuration;
import com.clemble.casino.management.Record;

import java.util.List;

/**
 * Created by mavarazy on 9/20/14.
 */
public interface RecordService<T extends Configuration> extends ClembleService {

    public List<? extends Record<T>> myRecords();

    public Record<T> get(String key);

}
