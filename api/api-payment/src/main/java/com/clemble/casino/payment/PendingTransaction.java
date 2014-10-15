package com.clemble.casino.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by mavarazy on 15/10/14.
 */
public class PendingTransaction implements PaymentTransactionAware {

    @Id
    final private String transactionKey;
    final private List<PendingOperation> operations;

    @JsonCreator
    public PendingTransaction(
        @JsonProperty("transactionKey") String transactionKey,
        @JsonProperty("operations") List<PendingOperation> operations) {
        this.transactionKey = transactionKey;
        this.operations = operations;
    }

    @Override
    public String getTransactionKey() {
        return transactionKey;
    }

    public List<PendingOperation> getOperations() {
        return operations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingTransaction)) return false;

        PendingTransaction that = (PendingTransaction) o;

        if (operations != null ? !operations.equals(that.operations) : that.operations != null) return false;
        if (transactionKey != null ? !transactionKey.equals(that.transactionKey) : that.transactionKey != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionKey != null ? transactionKey.hashCode() : 0;
        result = 31 * result + (operations != null ? operations.hashCode() : 0);
        return result;
    }
}
