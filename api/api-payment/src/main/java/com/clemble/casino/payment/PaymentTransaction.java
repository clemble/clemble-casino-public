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

import com.clemble.casino.VersionAware;
import com.clemble.casino.error.ClembleCasinoError.Code;
import com.clemble.casino.payment.validation.DebitMatchCreditConstraint;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "PAYMENT_TRANSACTION")
public class PaymentTransaction implements PaymentTransactionAware {

    /**
     * Generated 05/05/13
     */
    private static final long serialVersionUID = 2610517770966910840L;


    @Id
    private String transactionKey;

    @DebitMatchCreditConstraint
    private Set<PaymentOperation> operations = new HashSet<PaymentOperation>();
    private Date transactionDate;
    private Date processingDate = new Date();

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

    public Set<PaymentOperation> getOperations() {
        return operations;
    }

    public PaymentOperation getOperation(String player) {
        // Step 1. Sanity check
        if (player == null)
            return null;
        // Step 2. Processing payment
        for (PaymentOperation paymentOperation : operations)
            if (paymentOperation.getPlayer().equals(player))
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
            PaymentOperation playerOperation = getOperation(paymentOperation.getPlayer());
            if(playerOperation != null) {
                operations.remove(playerOperation);
                operations.add(playerOperation.combine(paymentOperation));
            } else {
                this.operations.add(paymentOperation);
            }
        }
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
