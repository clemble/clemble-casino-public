package com.clemble.casino.error;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ClembleCasinoFailureDescription {

    final public static ClembleCasinoFailureDescription SERVER_ERROR = new ClembleCasinoFailureDescription().addProblem(new ClembleCasinoFailure(ClembleCasinoError.ServerError));

    final Set<ClembleCasinoFailure> failures = new HashSet<ClembleCasinoFailure>();

    public ClembleCasinoFailureDescription() {
    }

    public ClembleCasinoFailureDescription(Set<String> errorCodes) {
        this(ClembleCasinoError.forCodes(errorCodes));
    }

    public ClembleCasinoFailureDescription(Collection<ClembleCasinoError> errors) {
        for (ClembleCasinoError error : errors)
            this.failures.add(new ClembleCasinoFailure(error));
    }

    public Set<ClembleCasinoFailure> getProblems() {
        return failures;
    }

    public ClembleCasinoFailureDescription setProblems(Collection<ClembleCasinoFailure> failures) {
        this.failures.addAll(failures);
        return this;
    }

    public ClembleCasinoFailureDescription addProblem(ClembleCasinoFailure failure) {
        if (failure != null)
            this.failures.add(failure);
        return this;
    }

    public ClembleCasinoFailureDescription addError(ClembleCasinoError error) {
        this.failures.add(new ClembleCasinoFailure(error));
        return this;
    }

    public ClembleCasinoFailureDescription setErrors(Collection<ClembleCasinoError> errors) {
        for (ClembleCasinoError error : errors)
            this.failures.add(new ClembleCasinoFailure(error));
        return this;
    }

    @Override
    public String toString() {
        return "GogomayaFailureDescription [failures=" + failures + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((failures == null) ? 0 : failures.hashCode());
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
        ClembleCasinoFailureDescription other = (ClembleCasinoFailureDescription) obj;
        if (failures == null) {
            if (other.failures != null)
                return false;
        } else if (!failures.equals(other.failures))
            return false;
        return true;
    }

}
