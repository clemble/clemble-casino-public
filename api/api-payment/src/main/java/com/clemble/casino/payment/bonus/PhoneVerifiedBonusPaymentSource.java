package com.clemble.casino.payment.bonus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/8/14.
 */
@JsonTypeName(PhoneVerifiedBonusPaymentSource.JSON_TYPE)
public class PhoneVerifiedBonusPaymentSource implements BonusPaymentSource {

    final public static String JSON_TYPE = "payment:bonus:phone:verified";

    final public static PhoneVerifiedBonusPaymentSource INSTANCE = new PhoneVerifiedBonusPaymentSource();

    @Override
    public String toTransactionKey(String player) {
        return player + ":phone";
    }

    @JsonCreator
    public static PhoneVerifiedBonusPaymentSource getInstance(@JsonProperty("type") String betType) {
        return INSTANCE;
    }

    @Override
    public int hashCode(){
        return 31;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == PhoneVerifiedBonusPaymentSource.class;
    }

}
