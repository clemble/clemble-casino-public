package com.clemble.casino.payment;

import com.clemble.casino.VersionAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by mavarazy on 15/10/14.
 */
public class PendingTransaction implements PaymentTransactionAware, VersionAware {

    /**
     * Generated 16/10/14
     */
    private static final long serialVersionUID = 6508845694631953306L;

    @Id
    final private String transactionKey;
    final private List<PaymentOperation> operations;
    final private Integer version;

    @JsonCreator
    public PendingTransaction(
        @JsonProperty("transactionKey") String transactionKey,
        @JsonProperty("operations") List<PaymentOperation> operations,
        @JsonProperty("version") Integer version) {
        this.transactionKey = transactionKey;
        this.operations = operations;
        this.version = version;
    }

    @Override
    public String getTransactionKey() {
        return transactionKey;
    }

    public List<PaymentOperation> getOperations() {
        return operations;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingTransaction)) return false;

        PendingTransaction that = (PendingTransaction) o;

        if (operations != null ? !operations.equals(that.operations) : that.operations != null) return false;
        if (transactionKey != null ? !transactionKey.equals(that.transactionKey) : that.transactionKey != null)
            return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionKey != null ? transactionKey.hashCode() : 0;
        result = 31 * result + (operations != null ? operations.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

}
