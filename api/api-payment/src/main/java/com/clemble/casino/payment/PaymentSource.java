package com.clemble.casino.payment;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 12/1/14.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface PaymentSource {

    public String toTransactionKey(String player);

}
