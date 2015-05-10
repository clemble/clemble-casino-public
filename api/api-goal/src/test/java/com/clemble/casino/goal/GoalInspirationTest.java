package com.clemble.casino.goal;

import com.clemble.casino.goal.lifecycle.management.GoalInspiration;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by mavarazy on 5/10/15.
 */
public class GoalInspirationTest {

    @Test
    public void testComparison() {
        // Step 1. Creating 2 inspirations
        GoalInspiration A = new GoalInspiration("A", "Go C", DateTime.now().minusDays(1));
        GoalInspiration B = new GoalInspiration("B", "Go C", DateTime.now());
        // Step 2. Adding to tree set
        Set<GoalInspiration> inspirationSet = new TreeSet<GoalInspiration>();
        inspirationSet.add(A);
        inspirationSet.add(B);
        // Step 3. Processing in natural order
        Iterator<GoalInspiration> inspirationIterator = inspirationSet.iterator();
        Assert.assertEquals(inspirationIterator.next(), B);
        Assert.assertEquals(inspirationIterator.next(), A);
    }

}
