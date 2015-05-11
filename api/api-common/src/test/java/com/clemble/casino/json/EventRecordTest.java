package com.clemble.casino.json;

import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.clemble.casino.lifecycle.record.EventRecord;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by mavarazy on 5/11/15.
 */
public class EventRecordTest {

    @Test
    public void testSorting() {
        // Step 1. Generating event records
        EventRecord A = new EventRecord(new BetAction(3), DateTime.now().minusDays(1));
        EventRecord B = new EventRecord(new BetAction(4), DateTime.now().minusDays(3));
        EventRecord C = new EventRecord(new BetAction(5), DateTime.now());
        // Step 2. Creating sorted set
        SortedSet<EventRecord> eventRecords = new TreeSet<EventRecord>();
        eventRecords.add(B);
        eventRecords.add(C);
        eventRecords.add(A);
        // Step 3. Checking order
        Iterator<EventRecord> recordIterator = eventRecords.iterator();
        Assert.assertEquals(recordIterator.next(), C);
        Assert.assertEquals(recordIterator.next(), A);
        Assert.assertEquals(recordIterator.next(), B);
    }
}
