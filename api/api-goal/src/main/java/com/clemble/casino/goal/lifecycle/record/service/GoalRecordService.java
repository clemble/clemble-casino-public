package com.clemble.casino.goal.lifecycle.record.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.goal.lifecycle.record.GoalRecord;
import com.clemble.casino.lifecycle.record.RecordState;

import java.util.List;

/**
 * Created by mavarazy on 9/20/14.
 */
public interface GoalRecordService extends ClembleService {

    List<GoalRecord> myRecords();

    List<GoalRecord> myRecordsWithState(RecordState state);

    GoalRecord get(String key);

}
