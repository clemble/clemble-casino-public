package com.clemble.casino.error;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.Validation;

/**
 * Created by mavarazy on 6/18/14.
 */
public class ValidationTest {

    @Test
    public void testInitialized() {
        Assert.assertNotNull(Validation.buildDefaultValidatorFactory());
    }

}
