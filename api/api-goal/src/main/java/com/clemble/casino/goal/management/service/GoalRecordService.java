package com.clemble.casino.goal.management.service;

import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.management.GoalRecord;
import com.clemble.casino.lifecycle.record.service.RecordService;

import java.util.List;

/**
 * Created by mavarazy on 9/20/14.
 */
public interface GoalRecordService extends RecordService<GoalConfiguration> {

    List<GoalRecord> myRecords();

    GoalRecord get(String key);

}
