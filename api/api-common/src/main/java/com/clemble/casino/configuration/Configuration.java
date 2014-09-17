package com.clemble.casino.configuration;

import com.clemble.casino.money.Money;
import com.clemble.casino.rule.privacy.PrivacyRule;
import com.clemble.casino.rule.time.MoveTimeRule;
import com.clemble.casino.rule.time.TotalTimeRule;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * Created by mavarazy on 9/6/14.
 */
public interface Configuration extends Serializable {

    public PrivacyRule getPrivacyRule();

}
