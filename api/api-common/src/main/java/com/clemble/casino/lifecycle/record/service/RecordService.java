package com.clemble.casino.lifecycle.record.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.record.Record;

import java.util.List;

/**
 * Created by mavarazy on 9/20/14.
 */
public interface RecordService<T extends Configuration> extends ClembleService {

    public List<? extends Record<T>> myRecords();

    public Record<T> get(String key);

}
