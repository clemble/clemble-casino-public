package com.clemble.casino.payment.bonus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/1/14.
 */
@JsonTypeName(value = SocialAddedBonusPaymentSource.JSON_TYPE)
public class SocialAddedBonusPaymentSource implements BonusPaymentSource {

    final public static String JSON_TYPE = "payment:bonus:social:add";

    final private String provider;

    @JsonCreator
    public SocialAddedBonusPaymentSource(@JsonProperty("provider") String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    @Override
    public String toTransactionKey(String player) {
        return player + ":" + provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialAddedBonusPaymentSource that = (SocialAddedBonusPaymentSource) o;

        return !(provider != null ? !provider.equals(that.provider) : that.provider != null);

    }

    @Override
    public int hashCode() {
        return provider != null ? provider.hashCode() : 0;
    }

}
