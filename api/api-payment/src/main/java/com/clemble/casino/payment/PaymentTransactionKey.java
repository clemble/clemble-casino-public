package com.clemble.casino.payment;

import com.clemble.casino.Key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PaymentTransactionKey implements Key {

    /**
     * Generated 05/05/13
     */
    private static final long serialVersionUID = 1305768471476355952L;

    @Column(name = "TRANSACTION_ID")
    private String transaction;

    @Column(name = "TRANSACTION_SOURCE")
    private String source;

    public PaymentTransactionKey() {
    }

    public PaymentTransactionKey(String source, long transactionId) {
        this(source, String.valueOf(transactionId));
    }

    public PaymentTransactionKey(Enum<?> source, String transactionId) {
        this(source.name(), String.valueOf(transactionId));
    }

    public PaymentTransactionKey(String source, String transactionId) {
        this.source = source;
        this.transaction = transactionId;
    }

    public String getTransaction() {
        return transaction;
    }

    public PaymentTransactionKey setTransaction(String transactionId) {
        this.transaction = transactionId;
        return this;
    }

    public String getSource() {
        return source;
    }

    public PaymentTransactionKey setSource(String source) {
        this.source = source;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((transaction == null) ? 0 : transaction.hashCode());
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
        PaymentTransactionKey other = (PaymentTransactionKey) obj;
        return source.equals(other.source) && transaction.equals(other.transaction);
    }

    @Override
    public String toString() {
        return "pt:" + source + ":" + transaction;
    }

}
