package com.clemble.casino.payment.bonus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/6/14.
 */
@JsonTypeName(EmailVerifiedBonusPaymentSource.JSON_TYPE)
public class EmailVerifiedBonusPaymentSource implements BonusPaymentSource {

    final public static String JSON_TYPE = "payment:bonus:email:verified";

    final public static EmailVerifiedBonusPaymentSource INSTANCE = new EmailVerifiedBonusPaymentSource();

    @Override
    public String toTransactionKey(String player) {
        return player + ":email";
    }

    @JsonCreator
    public static EmailVerifiedBonusPaymentSource getInstance(@JsonProperty("type") String betType) {
        return INSTANCE;
    }

    @Override
    public int hashCode(){
        return 31;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == EmailVerifiedBonusPaymentSource.class;
    }

}
