package com.clemble.casino.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClembleCasinoFailure {

    final public static ClembleCasinoFailure SERVER_ERROR = ClembleCasinoFailure.withErrors(Collections.singleton(ClembleCasinoError.ServerError));

    final private Set<ClembleCasinoFieldError> fields;
    final private Set<ClembleCasinoError> server;

    @JsonCreator
    public ClembleCasinoFailure(
            @JsonProperty("fields") Set<ClembleCasinoFieldError> fields,
            @JsonProperty("server") Set<ClembleCasinoError> server) {
        this.fields = fields;
        this.server = server;
    }

    public Set<ClembleCasinoFieldError> getFields() {
        return fields;
    }

    public Set<ClembleCasinoError> getServer() {
        return server;
    }

    public static ClembleCasinoFailure withErrorCodes(Collection<String> errorCodes) {
        return withErrors(ClembleCasinoError.forCodes(errorCodes));
    }

    public static ClembleCasinoFailure withErrors(Collection<ClembleCasinoError> errors) {
        return new ClembleCasinoFailure(new HashSet<ClembleCasinoFieldError>(), new HashSet<ClembleCasinoError>(errors));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClembleCasinoFailure)) return false;

        ClembleCasinoFailure that = (ClembleCasinoFailure) o;

        if (fields != null ? !fields.equals(that.fields) : that.fields != null) return false;
        if (server != null ? !server.equals(that.server) : that.server != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fields != null ? fields.hashCode() : 0;
        result = 31 * result + (server != null ? server.hashCode() : 0);
        return result;
    }
}
