package com.clemble.casino.payment;

import com.clemble.casino.VersionAware;
import com.clemble.casino.money.Currency;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Set;

/**
 * Created by mavarazy on 15/10/14.
 *
 * After thought, all this can be replaced by creating PaymentTransaction prior to processing
 * there for this can be ignored at the end.
 */
public class PendingTransaction implements AccountTransaction, VersionAware {

    /**
     * Generated 16/10/14
     */
    private static final long serialVersionUID = 6508845694631953306L;

    @Id
    final private String transactionKey;
    final private Set<PaymentOperation> operations;
    final private Integer version;

    @JsonCreator
    public PendingTransaction(
        @JsonProperty("transactionKey") String transactionKey,
        @JsonProperty("operations") Set<PaymentOperation> operations,
        @JsonProperty("version") Integer version) {
        this.transactionKey = transactionKey;
        this.operations = operations;
        this.version = version;
    }

    @Override
    public String getTransactionKey() {
        return transactionKey;
    }

    @Override
    public Set<PaymentOperation> getOperations() {
        return operations;
    }

    @Override
    public PaymentOperation getOperation(String player, Currency currency) {
        // Step 1. Sanity check
        if (player == null)
            return null;
        // Step 2. Processing payment
        for (PaymentOperation paymentOperation : operations)
            if (paymentOperation.getPlayer().equals(player) && paymentOperation.getAmount().getCurrency() == currency)
                return paymentOperation;
        // Step 3. Returning nothing
        return null;
    }

    public PendingTransaction addOperation(PaymentOperation paymentOperation) {
        if (paymentOperation != null) {
            PaymentOperation playerOperation = getOperation(paymentOperation.getPlayer(), paymentOperation.getAmount().getCurrency());
            if(playerOperation != null) {
                operations.remove(playerOperation);
                operations.add(playerOperation.combine(paymentOperation));
            } else {
                this.operations.add(paymentOperation);
            }
        }
        return this;
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
        return !(version != null ? !version.equals(that.version) : that.version != null);

    }

    @Override
    public int hashCode() {
        int result = transactionKey != null ? transactionKey.hashCode() : 0;
        result = 31 * result + (operations != null ? operations.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return transactionKey + ":" + operations + ":" + version;
    }
}
