package com.clemble.casino.payment;

import java.util.HashSet;
import java.util.Set;

import com.clemble.casino.money.Currency;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.annotation.Id;

public class PaymentTransaction implements AccountTransaction, PaymentTransactionAware, PaymentSourceAware {

    /**
     * Generated 05/05/13
     */
    private static final long serialVersionUID = 2610517770966910840L;

    @Id
    private String transactionKey;

    private PaymentSource source;
    private Set<PaymentOperation> operations = new HashSet<PaymentOperation>();

    private DateTime transactionDate;
    private DateTime processingDate = DateTime.now(DateTimeZone.UTC);

    public PaymentTransaction() {
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public PaymentTransaction setTransactionKey(String transactionId) {
        this.transactionKey = transactionId;
        return this;
    }

    public boolean isParticipant(String player) {
        for (PaymentOperation paymentOperation : operations)
            if (paymentOperation.getPlayer().equals(player))
                return true;
        return false;
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

    public PaymentTransaction setOperations(Set<PaymentOperation> operations) {
        this.operations = operations;
        return this;
    }

    public PaymentTransaction addOperation(PaymentOperation paymentOperation) {
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

    public DateTime getTransactionDate() {
        return transactionDate;
    }

    public PaymentTransaction setTransactionDate(DateTime transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public DateTime getProcessingDate() {
        return processingDate;
    }

    public PaymentTransaction setProcessingDate(DateTime processingDate) {
        this.processingDate = processingDate;
        return this;
    }

    @Override
    public PaymentSource getSource() {
        return source;
    }

    public PaymentTransaction setSource(PaymentSource source) {
        this.source = source;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
        result = prime * result + ((transactionKey == null) ? 0 : transactionKey.hashCode());
        result = prime * result + ((operations == null) ? 0 : operations.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaymentTransaction other = (PaymentTransaction) obj;
        if (transactionDate == null) {
            if (other.transactionDate != null)
                return false;
        } else if (!transactionDate.equals(other.transactionDate))
            return false;
        if (transactionKey == null) {
            if (other.transactionKey != null)
                return false;
        } else if (!transactionKey.equals(other.transactionKey))
            return false;
        if (operations == null) {
            if (other.operations != null)
                return false;
        } else if (!operations.equals(other.operations))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "payment:" + transactionKey + ":" + operations + ":" + transactionDate + ":" + processingDate;
    }

}
