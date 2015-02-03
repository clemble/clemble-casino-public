package com.clemble.casino.tag;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mavarazy on 2/3/15.
 */
public class TagUtilsTest {

    @Test
    public void testTagParsing() {
        Assert.assertEquals(TagUtils.getTag("#sport"), "sport");
        Assert.assertEquals(TagUtils.getTag("#sport  "), "sport");
        Assert.assertEquals(TagUtils.getTag("  #sport  "), "sport");
        Assert.assertEquals(TagUtils.getTag("  #sport,  "), "sport");
        Assert.assertEquals(TagUtils.getTag("  #sport.  "), "sport");
        Assert.assertEquals(TagUtils.getTag("  #sport\n   "), "sport");
        Assert.assertEquals(TagUtils.getTag("  #sport\t   "), "sport");
        Assert.assertEquals(TagUtils.getTag("sport"), null);
    }

}
