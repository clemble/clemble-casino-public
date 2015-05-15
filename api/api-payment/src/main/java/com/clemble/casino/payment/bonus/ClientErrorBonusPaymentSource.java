package com.clemble.casino.payment.bonus;

import com.clemble.casino.CreatedAware;
import com.clemble.casino.error.ClembleCasinoError;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.joda.time.DateTime;

/**
 * Created by mavarazy on 5/15/15.
 */
@JsonTypeName(ClientErrorBonusPaymentSource.JSON_TYPE)
public class ClientErrorBonusPaymentSource implements BonusPaymentSource, CreatedAware {

    final public static String JSON_TYPE = "payment:bonus:client:error";

    final private static String DATE_FORMAT = "ddMMyy";

    final private ClembleCasinoError code;
    final private DateTime created;

    @JsonCreator
    public ClientErrorBonusPaymentSource(
        @JsonProperty("code") ClembleCasinoError code,
        @JsonProperty("created") DateTime created) {
        this.code = code;
        this.created = created;
    }

    public ClembleCasinoError getCode() {
        return code;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    @Override
    public String toTransactionKey(String player) {
        return player + ":" + "client:error:" + code.getCode() + ":" + created.toString(DATE_FORMAT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientErrorBonusPaymentSource)) return false;

        ClientErrorBonusPaymentSource that = (ClientErrorBonusPaymentSource) o;

        if (code != that.code) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

}
