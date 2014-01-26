package com.clemble.casino.payment;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.clemble.casino.error.ClembleCasinoError.Code;
import com.clemble.casino.error.validation.DebitMatchCreditConstraint;

@Entity
@Table(name = "PAYMENT_TRANSACTION")
public class PaymentTransaction implements PaymentTransactionAware, Serializable {

    /**
     * Generated 05/05/13
     */
    private static final long serialVersionUID = 2610517770966910840L;

    @EmbeddedId
    private PaymentTransactionKey transactionKey;

    @DebitMatchCreditConstraint
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PAYMENT_TRANSACTION_OPERATION", joinColumns = { @JoinColumn(name = "TRANSACTION_ID"), @JoinColumn(name = "MONEY_SOURCE") })
    private Set<PaymentOperation> paymentOperations = new HashSet<PaymentOperation>();

    @NotNull(message = Code.PAYMENT_TRANSACTION_TRANSACTION_DATE_MISSING)
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @NotNull(message = Code.PAYMENT_TRANSACTION_PROCESSING_DATE_MISSING)
    @Column(name = "TRANSACTION_PROCESSING_DATE")
    private Date processingDate = new Date();

    public PaymentTransactionKey getTransactionKey() {
        return transactionKey;
    }

    public PaymentTransaction setTransactionKey(PaymentTransactionKey transactionId) {
        this.transactionKey = transactionId;
        return this;
    }

    public boolean isParticipant(String player) {
        for (PaymentOperation paymentOperation : paymentOperations)
            if (paymentOperation.getPlayer().equals(player))
                return true;
        return false;
    }

    public Set<PaymentOperation> getPaymentOperations() {
        return paymentOperations;
    }

    public PaymentOperation getPaymentOperation(String player) {
        // Step 1. Sanity check
        if (player == null)
            return null;
        // Step 2. Processing payment
        for (PaymentOperation paymentOperation : paymentOperations)
            if (paymentOperation.getPlayer().equals(player))
                return paymentOperation;
        // Step 3. Returning nothing
        return null;
    }

    public PaymentTransaction setPaymentOperations(Set<PaymentOperation> paymentOperations) {
        this.paymentOperations = paymentOperations;
        return this;
    }

    public PaymentTransaction addPaymentOperation(PaymentOperation paymentOperation) {
        if (paymentOperation != null)
            this.paymentOperations.add(paymentOperation);
        return this;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public PaymentTransaction setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public PaymentTransaction setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
        result = prime * result + ((transactionKey == null) ? 0 : transactionKey.hashCode());
        result = prime * result + ((paymentOperations == null) ? 0 : paymentOperations.hashCode());
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
        if (paymentOperations == null) {
            if (other.paymentOperations != null)
                return false;
        } else if (!paymentOperations.equals(other.paymentOperations))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "payment:" + transactionKey + ":" + paymentOperations + ":" + transactionDate + ":" + processingDate;
    }

}
