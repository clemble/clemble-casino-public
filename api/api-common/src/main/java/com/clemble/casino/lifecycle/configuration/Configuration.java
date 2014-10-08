package com.clemble.casino.lifecycle.configuration;

import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;

import java.io.Serializable;

/**
 * Created by mavarazy on 9/6/14.
 */
public interface Configuration extends Serializable {

    public PrivacyRule getPrivacyRule();

}
